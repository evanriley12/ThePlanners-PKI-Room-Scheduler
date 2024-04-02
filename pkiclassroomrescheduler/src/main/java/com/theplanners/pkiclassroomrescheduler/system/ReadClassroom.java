package com.theplanners.pkiclassroomrescheduler.system;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import java.io.InputStreamReader;

@Service
public class ReadClassroom {

    private final ClassroomList classroomList;
    private static final String CSV_FILE_PATH = "ClassroomInformation.csv";

    public ReadClassroom(ClassroomList classroomList) {
        this.classroomList = classroomList;
    }

    private String[] stringToArray(String input)
    {   
        if(input != ""){
        String[] elements = input.substring(1, input.length() - 1).split(","); 
        String[] arrayOfStrings = new String[elements.length];
        for (int i = 0; i < elements.length; i++) {
            arrayOfStrings[i] = elements[i].trim();
        }
        return arrayOfStrings;
        }
        else{
            return new String[0];
        }
    }

    public void readClassroomCSV(){
        if(classroomList != null){
            classroomList.clearClassrooms();
        }
        try { 
            ClassPathResource resource = new ClassPathResource(CSV_FILE_PATH);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            CSVReader csvReader = new CSVReader(reader); 
            String[] nextRecord; 
            Classroom nextClassroom = null;
  
            while ((nextRecord = csvReader.readNext()) != null) { 
                if(nextRecord[0].matches("-?\\d+(\\.\\d+)?"))
                {
                    String[] connectivity = stringToArray(nextRecord[4]);
                    String[] displays = stringToArray(nextRecord[5]);
                    nextClassroom = new Classroom(Integer.parseInt(nextRecord[0]), Integer.parseInt(nextRecord[1]), Integer.parseInt(nextRecord[2]), nextRecord[3], connectivity, displays);
                    classroomList.addClassroom(nextClassroom);
                    System.out.println(nextClassroom.toString());
                } 
            } 
            csvReader.close();
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}
