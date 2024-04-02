package com.theplanners.pkiclassroomrescheduler.system;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.theplanners.pkiclassroomrescheduler.system.MeetingTimeConverter.MeetingTime;

import java.io.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final Schedule schedule;
    private final ClassroomList classroomList;

    public FileUploadController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    @PostMapping("/upload")
    public ArrayList<Section> uploadFile(@RequestParam("file") MultipartFile file) {
        schedule.clearSchedule();
        ReadClassroom readClassroom = new ReadClassroom(classroomList);
        readClassroom.readClassroomCSV();

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
                    nextRow[6], Integer.parseInt(nextRow[7].replaceAll("[^\\d.]", "")), nextRow[8], nextRow[9], meetingTime.days, 
                    meetingTime.startTime, meetingTime.endTime, nextRow[13], 
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


