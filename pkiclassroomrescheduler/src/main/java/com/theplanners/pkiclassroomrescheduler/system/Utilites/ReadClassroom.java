package com.theplanners.pkiclassroomrescheduler.system.Utilites;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Classroom;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Read classroom is a utility class that reads in the static ClassroomInformation.csv 
 */
@Service
public final class ReadClassroom {


    /**
     * Filepath to the static resource containing the classroom information
     */
    //private static final String CSV_FILE_PATH = "ClassroomInformation.csv";
    private static final String CSV_FILE_PATH = "ClassroomInfoTest.csv"; // using this to test recursive output, remove when done

    /**
     * Utility function that takes a String with comma delimiters 
     * and converts it to and array of Strings
     * 
     * @param input String that is comma delimited
     * @return an array of comma delimited strings
     */
    private static String[] stringToArray(String input)
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

    /**
     * Given a ClassroomList object, reads the classrooms from the CSV_FILE_PATH and sets it as 
     * the ClassroomList's classroomList
     * 
     * @param classroomList classroomList object to add classrooms to
     * 
     * @see package com.theplanners.pkiclassroomrescheduler.system.ClassroomList
     */
    public static void readClassroomCSV(ClassroomList classroomList){
        ArrayList<Classroom> readClassroomList = new ArrayList<Classroom>();
        ClassPathResource resource = new ClassPathResource(CSV_FILE_PATH);
        String[] nextRecord; 
        Classroom nextClassroom = null;
        try {
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            CSVReader csvReader = new CSVReader(reader); 
            while ((nextRecord = csvReader.readNext()) != null) { 
                if(nextRecord[0].matches("-?\\d+(\\.\\d+)?"))
                    {
                    String[] connectivity = stringToArray(nextRecord[4]);
                    String[] displays = stringToArray(nextRecord[5]);
                    nextClassroom = new Classroom(Integer.parseInt(nextRecord[0]), Integer.parseInt(nextRecord[1]), Integer.parseInt(nextRecord[2]), nextRecord[3], connectivity, displays);
                    readClassroomList.add(nextClassroom);
                } 
            } 
            
            csvReader.close();
        } catch (IOException | CsvValidationException | NumberFormatException e) {
            e.printStackTrace();
        }
        if (classroomList == null){
            classroomList = new ClassroomList();
        }
        classroomList.setClassrooms(readClassroomList);
    }
}
