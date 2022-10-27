package FilesProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import Entities.Project;
import Entities.Stage;

public class ExcelFileReader{    
    
    public static void main(String[] args) throws ParseException {
        
        
        ArrayList<Project> projects = new ArrayList<Project>();         
            
        projects = readProject();
            
    }
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
       
        
        
        
        return null;
    }
}
