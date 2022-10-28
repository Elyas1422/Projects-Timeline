package FilesProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import Entities.Project;
import Entities.Stage;

public class ExcelFileReader{    
    

    public static ArrayList<Project> readProject() throws ParseException {

       try {

           
           String baseDirectory = System.getProperty("user.dir");
           String excelFilePath = baseDirectory + "/data/projects.xls";         
           System.out.println("Working Directory = " + baseDirectory);
           System.out.println("excelFilePath " + excelFilePath);
           
           
           FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
           Workbook wb = WorkbookFactory.create(inputStream);

           Sheet sheet = wb.getSheetAt(0);  // << gets the first sheet in the workbook
           DataFormatter formatter = new DataFormatter();
           int rows = sheet.getLastRowNum();
           
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
    
    public static ArrayList<Stage> readStages() throws ParseException{
       
        String baseDirectory = System.getProperty("user.dir");
        String excelFilePath = baseDirectory + "/data/Stages.xls";         
        System.out.println("Working Directory = " + baseDirectory);
        System.out.println("excelFilePath " + excelFilePath);
        
        try {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook wb = WorkbookFactory.create(inputStream);

        Sheet sheet = wb.getSheetAt(0);  
        
        Date date = null;
        Date time = null;
        ArrayList<Stage> stages = new ArrayList<Stage>();
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
            
            Date[] dateTime = StagesDetail(documentNumber); date = dateTime[0]; time = dateTime[1];
            Stage newStage = new Stage(changeIndicator, date, documentNumber,
                    fieldName, newValue, objectValue,
                    oldValue, textFlag, time );
            stages.add(newStage);
        }
        
        return stages;
        
      } catch(IOException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public static Date[] StagesDetail(int docNumber) throws ParseException{
        
        String baseDirectory = System.getProperty("user.dir");
        String excelFilePath = baseDirectory + "/data/Stages_Detailed.xls";         
        System.out.println("Working Directory = " + baseDirectory);
        System.out.println("excelFilePath " + excelFilePath);
        
        try {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook wb = WorkbookFactory.create(inputStream);

        Sheet sheet = wb.getSheetAt(0);  
        DataFormatter formatter = new DataFormatter();
        int rows = sheet.getLastRowNum();        
        
        Date[] info = new Date[2];
        Date date, time;
        date = time = null;
        boolean firstRow = true;
        boolean skipRow = false;
        boolean found = false;
        
        for (Row row : sheet) {
            
            if(found) {break;}
            if(firstRow) {firstRow=false; continue;}
            skipRow = false;
            
            for (Cell cell : row) {
                
                if(skipRow) continue;
                
                String letter = CellReference.convertNumToColString(cell.getColumnIndex());
                String text = formatter.formatCellValue(cell);
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                switch (letter) {
                    case "B":
                        if(docNumber!=cell.getNumericCellValue())
                            skipRow=true;
                        break;
                    case "C":
                        date = cell.getDateCellValue();
                        info[0]=date;
                        break;
                    case "D":
                        SimpleDateFormat frame = new SimpleDateFormat("hh:mm:ss aa");
                        time = frame.parse(frame.format(cell.getDateCellValue()));
                        info[1]=time;
                        found=true;
                    default:
                        System.out.println("");
                }
            }
            
        }
        
        return info;
        
      } catch(IOException e){
            System.out.println(e.toString());
            return null;
        }
    }
}
