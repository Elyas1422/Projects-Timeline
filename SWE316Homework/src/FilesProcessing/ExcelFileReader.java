package FilesProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import Entities.Project;
import Entities.ProjectStage;

public class ExcelFileReader{    


    public static ArrayList<Project> readProject() throws ParseException {

        try {


            String baseDirectory = System.getProperty("user.dir");
            String excelFilePath = baseDirectory + "/data/projects.xls";         


            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);  

            ArrayList<Project> projects = new ArrayList<Project>();

            boolean firstRow = true;

            for (Row row : sheet) {

                if(firstRow) {firstRow=false; continue;}
                int stage = 0;
                int customer = 0;
                String nodeID,customerProjectID, currency;
                nodeID = customerProjectID = currency = "";
                Date startDate, endDate, createdOn, changedOn;
                startDate = endDate = createdOn = changedOn = null;
                for (Cell cell : row) {

                    String letter = CellReference.convertNumToColString(cell.getColumnIndex());

                    switch (letter) {
                        case "A":
                            nodeID = cell.getRichStringCellValue().getString();
                            break;
                        case "B":
                            customerProjectID = cell.getRichStringCellValue().getString();
                            break;
                        case "C":
                            stage = (int) cell.getNumericCellValue();
                            break;
                        case "D":
                            startDate = cell.getDateCellValue();
                            break;
                        case "E":
                            endDate = cell.getDateCellValue();
                            break;
                        case "F":
                            customer = (int) cell.getNumericCellValue();
                            break;
                        case "G":
                            currency = cell.getRichStringCellValue().getString();
                            break;
                        case "H":
                            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            createdOn = format.parse(cell.getRichStringCellValue().getString());
                            break;
                        case "I":
                            SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            changedOn = format2.parse(cell.getRichStringCellValue().getString());
                            break;
                        default:
                            System.out.println("Unknown");
                    }
                }
                Project newProject= new Project(changedOn, createdOn, currency, customer, customerProjectID, endDate, nodeID, stage, startDate);
                projects.add(newProject);
            }
            return projects;


        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e.toString());
            return null;
        }
    }

    public static ArrayList<ProjectStage> readStages() throws ParseException{

        String baseDirectory = System.getProperty("user.dir");
        String excelFilePath = baseDirectory + "/data/Stages.xls";         

        try {

            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);  

            Date date = null;
            Date time = null;
            ArrayList<ProjectStage> stages = new ArrayList<ProjectStage>();
            boolean firstRow = true;

            for (Row row : sheet) {

                if(firstRow) {firstRow=false; continue;}

                int documentNumber, textFlag, newValue, oldValue;
                documentNumber = textFlag = newValue = oldValue = 0;
                char changeIndicator = ' ';
                String objectValue,fieldName;
                objectValue = fieldName = "";

                for (Cell cell : row) {

                    String letter = CellReference.convertNumToColString(cell.getColumnIndex());

                    switch (letter) {
                        case "A":
                            objectValue = cell.getRichStringCellValue().getString();
                            break;
                        case "B":
                            documentNumber = (int) cell.getNumericCellValue();
                            break;
                        case "C":
                            fieldName = cell.getRichStringCellValue().getString();
                            break;
                        case "D":
                            changeIndicator = cell.getRichStringCellValue().getString().charAt(0);
                            break;
                        case "E":
                            textFlag = (int) cell.getNumericCellValue();
                            break;
                        case "F":
                            newValue = (int) cell.getNumericCellValue();
                            break;
                        case "G":
                            oldValue = (int) cell.getNumericCellValue();
                            break;
                        default:
                            System.out.println("Unknown");
                    }
                }

                ProjectStage newStage = new ProjectStage(changeIndicator, date,
                        fieldName, newValue, objectValue,
                        oldValue, textFlag, time,documentNumber );
                stages.add(newStage);
            }

            return mergeDetails(stages);

        } catch(IOException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public static Hashtable StagesDetail() throws ParseException{

        String baseDirectory = System.getProperty("user.dir");
        String excelFilePath = baseDirectory + "/data/Stages_Detailed.xls";         

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);         

            Hashtable<Integer, Date[]> details = new Hashtable<Integer, Date[]>();
            boolean firstRow = true;

            for (Row row : sheet) {

                if(firstRow) {firstRow=false; continue;}
                int docNumber = 0;
                Date date, time;
                Date[] info = new Date[2];
                date = time = null;

                for (Cell cell : row) {


                    String letter = CellReference.convertNumToColString(cell.getColumnIndex());
                    switch (letter) {
                        case "B":
                            docNumber = (int)cell.getNumericCellValue();
                            break;
                        case "C":
                            date = cell.getDateCellValue();
                            info[0]=date;
                            break;
                        case "D":
                            SimpleDateFormat frame = new SimpleDateFormat("hh:mm:ss aa");
                            time = frame.parse(frame.format(cell.getDateCellValue()));
                            info[1]=time;
                            details.put(docNumber, info);
                            continue;
                        default:
                            String  x= null;
                    }
                }

            }

            return details;

        } catch(IOException e){
            System.out.println(e.toString());
            return null;
        }
    }
    private static ArrayList<ProjectStage> mergeDetails(ArrayList<ProjectStage> stages) throws ParseException {
        Hashtable allDetails = StagesDetail();

        for (int i =0; i<stages.size();i++) {
            ProjectStage stage=stages.get(i);
            Date[] dateAndTime = (Date[]) allDetails.get(stage.getDocumentNumber());
            stage.setDate(dateAndTime[0]);
            stage.setTime(dateAndTime[1]);

        }
        return stages;
    }
}
