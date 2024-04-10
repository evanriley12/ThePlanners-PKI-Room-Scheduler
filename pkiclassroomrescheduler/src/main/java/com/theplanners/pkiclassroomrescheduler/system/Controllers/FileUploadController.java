package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.MeetingTimeConverter;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.ReadClassroom;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.MeetingTimeConverter.MeetingTime;

import java.io.*;
import java.util.ArrayList;

/**
 * FileUpload controller hosts and endpoint at /api/upload that allows the upload of 
 * a csv file containing the current course assignment information. The uploaded 
 * csv then gets parsed and saved as a Schedule
 * 
 * @see com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule
 */
@RestController
@RequestMapping("/api")
public class FileUploadController {

    /**
    * The Autowired Schedule instance used for managing course assignments
    */
    private final Schedule schedule;
    /**
     * The Autowired ClassroomList instance used for managing classroom information
     */
    private final ClassroomList classroomList;

    /**
    * Constructs a new FileUploadController with the provided Schedule and ClassroomList instances.
    * Uses injection to share across components
    * 
    * @param schedule The Schedule instance to use for managing course assignments.
    * @param classroomList The ClassroomList instance to use for managing classroom information.
    */
    public FileUploadController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    /**
     * Takes an uploaded csv file and reads each line, if it is a new section,
     * which is determined by a blank first cell, then a new section instance is
     * created and added to the schedule. Also reads classroom list at the time
     * of upload. 
     * 
     * @param file csv file that is uploaded through the /api/upload endpoint
     * @return the schedule generated from the csv
     * 
     * @see com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule
     * @see com.theplanners.pkiclassroomrescheduler.system.Entities.Section
     * @see com.theplanners.pkiclassroomrescheduler.system.Utilites.MeetingTimeConverter
     */
    @PostMapping("/upload")
    public ArrayList<Section> uploadFile(@RequestParam("file") MultipartFile file) {
        schedule.clearSchedule();
        ReadClassroom.readClassroomCSV(classroomList);

        if (file.isEmpty()) {
            return null;
        }

        try (InputStream inputStream = file.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(3).build()) {

            String[] nextRow;

            while ((nextRow = csvReader.readNext()) != null) { 
                if(nextRow[0] == ""){
                    MeetingTime meetingTime = MeetingTimeConverter.parseMeetingTime(nextRow[11]);
                    Section section = new Section(
                    nextRow[6], Integer.parseInt(nextRow[7].replaceAll("[^\\d.]", "")), nextRow[8], nextRow[9], meetingTime.getDays(), 
                    meetingTime.getStartTime(), meetingTime.getEndTime(), nextRow[13], 
                    Integer.parseInt(nextRow[14].replaceAll("[^\\d.]", "")), nextRow[18], nextRow[34], Integer.parseInt(nextRow[28]), Integer.parseInt(nextRow[29]));
                    schedule.addSection(section);
                }
            } 

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return schedule.returnSchedule();
    }

}


