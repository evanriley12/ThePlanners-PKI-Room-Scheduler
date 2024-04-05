package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.theplanners.pkiclassroomrescheduler.system.Algorithm;
import com.theplanners.pkiclassroomrescheduler.system.Classroom;
import com.theplanners.pkiclassroomrescheduler.system.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Section;

public class AlgorithmsTests {
     @Mock
    private Schedule schedule;

    @Mock
    private ClassroomList classroomList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoAlgorithm_null() {
        // Mock data
        ArrayList<Classroom> classrooms = new ArrayList<>();
        // Populate classrooms array with mock data

        Schedule mockSchedule = new Schedule();
        Section mockSection = new Section(null, 0, null, null, null, null, null, null, 0, null, null, 0, 0);
        mockSchedule.addSection(mockSection);

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Create instance of Algorithm
        Algorithm algorithm = new Algorithm(schedule, classroomList);

        // Invoke method under test
        String result = algorithm.doAlgorithm(mockSection, 50);

        // Assert the result
        assertEquals("No available classrooms.", result);
    }
    @Test
    void testDoAlgorithm_one_suitable() {
        // Mock data
        ArrayList<Classroom> classrooms = new ArrayList<>();
        // Populate classrooms array with mock data
        ArrayList<DayOfWeek> meetingDays = new ArrayList<DayOfWeek>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(10,0);

        Schedule mockSchedule = new Schedule();
        Section mockSection = new Section("test", 1, "test", "", meetingDays, start, end, null, 1, null, null, 10, 50);
        mockSchedule.addSection(mockSection);

        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 65, 0, null, null, null));

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Create instance of Algorithm
        Algorithm algorithm = new Algorithm(schedule, classroomList);

        // Invoke method under test
        String result = algorithm.doAlgorithm(mockSection, 50);

        String expected = "The best option is to move test Section: 1 to room 2.\n\nOther options with the same class size: None\n\nOther options with larger class size: None";

        // Assert the result
        assertEquals(expected, result);
    }

    @Test
    void testDoAlgorithm_more_suitable() {
        // Mock data
        ArrayList<Classroom> classrooms = new ArrayList<>();
        // Populate classrooms array with mock data
        ArrayList<DayOfWeek> meetingDays = new ArrayList<DayOfWeek>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(10,0);

        Schedule mockSchedule = new Schedule();
        Section mockSection = new Section("test", 1, "test", "", meetingDays, start, end, null, 1, null, null, 10, 50);
        mockSchedule.addSection(mockSection);

        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 65, 0, null, null, null));
        classrooms.add(new Classroom(3, 50, 0, null, null, null));
        classrooms.add(new Classroom(4, 100, 0, null, null, null));

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Create instance of Algorithm
        Algorithm algorithm = new Algorithm(schedule, classroomList);

        // Invoke method under test
        String result = algorithm.doAlgorithm(mockSection, 50);

        String expected = "The best option is to move test Section: 1 to room 3.\n\nOther options with the same class size: None\n\nOther options with larger class size: 2, 4";

        // Assert the result
        assertEquals(expected, result);
    }

    @Test
    void testDoAlgorithm_none_suitable() {
        // Mock data
        ArrayList<Classroom> classrooms = new ArrayList<>();
        // Populate classrooms array with mock data
        ArrayList<DayOfWeek> meetingDays = new ArrayList<DayOfWeek>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(10,0);

        Schedule mockSchedule = new Schedule();
        Section mockSection = new Section("test", 1, "test", "", meetingDays, start, end, null, 1, null, null, 10, 50);
        mockSchedule.addSection(mockSection);

        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 45, 0, null, null, null));


        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Create instance of Algorithm
        Algorithm algorithm = new Algorithm(schedule, classroomList);

        // Invoke method under test
        String result = algorithm.doAlgorithm(mockSection, 50);

        String expected = "No available classrooms.";

        // Assert the result
        assertEquals(expected, result);
    }
}
