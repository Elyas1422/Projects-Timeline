package Entities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import FilesProcessing.ExcelFileReader;

public class check {

    public static void main(String[] args) throws ParseException {
        ArrayList<Project> p = ExcelFileReader.readProject();
        p.get(0).setStages();
        System.out.println();
        ArrayList<ProjectStage> s = p.get(0).getStages();
        for(int i=0; i<s.size(); i++) {
            System.out.println(s.get(i).getDocumentNumber());
            System.out.println(s.get(i).getDate());
        }
   
    }

}
