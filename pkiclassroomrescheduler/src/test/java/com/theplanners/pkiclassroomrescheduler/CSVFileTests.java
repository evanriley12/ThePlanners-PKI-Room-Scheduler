package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.theplanners.pkiclassroomrescheduler.system.Course;
import com.theplanners.pkiclassroomrescheduler.system.FileUploadController;
import com.theplanners.pkiclassroomrescheduler.system.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Section;

@SpringBootTest
public class CSVFileTests {
    
    @Mock
    private Schedule schedule;

    @InjectMocks
    private FileUploadController fileUploadController;

    //Test to check whether file upload was successful
    @Test
    void uploadAndParseFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("testCSV.csv");
        InputStream inputStream = resource.getInputStream();

        Schedule mockSchedule = new Schedule();
        Course mockCourse = new Course("mock course");
        Section mockSection = new Section(null, 0, null, null, null, null, null, null, 0, null, null, 0, 0);
        mockCourse.addSection(mockSection);
        mockSchedule.addCourse(mockCourse);

        @SuppressWarnings("null")
        MultipartFile file = new MockMultipartFile(resource.getFilename(), inputStream);

        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());

        ArrayList<Section> sections = fileUploadController.uploadFile(file);

        assertNotNull(sections);

        assertEquals(1, sections.size());


    }

    // Test to check whether uploading an empty file returns null sections
    @Test
    void uploadEmptyFile() throws Exception {
        MultipartFile file = new MockMultipartFile("empty.csv", new byte[0]);

        ArrayList<Section> sections = fileUploadController.uploadFile(file);

        assertEquals(null, sections);
    }
}