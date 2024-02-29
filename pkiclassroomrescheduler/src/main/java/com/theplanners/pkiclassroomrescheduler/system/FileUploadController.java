package com.theplanners.pkiclassroomrescheduler.system;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @PostMapping("/upload")
    public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) {
        List<String[]> parsedData = new ArrayList<>();

        if (file.isEmpty()) {
            return parsedData;
        }

        try (InputStream inputStream = file.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader)) {

            parsedData = csvReader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();

        }

        return parsedData;
    }
}


