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
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Entities.Classroom;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Result;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.Algorithm;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.ReadClassroom;

@SpringBootTest
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
        mockSchedule.updateCrosslist();

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Invoke method under test
        ArrayList<Result> result = Algorithm.doAlgorithm(mockSection, 50, schedule, classroomList);

        // Assert the result
        assertEquals(null, result);
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
        mockSchedule.updateCrosslist();
        
        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 65, 0, null, null, null));

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Invoke method under test
        ArrayList<Result> result = Algorithm.doAlgorithm(mockSection, 50, schedule, classroomList);

        ArrayList<Result> expected = new ArrayList<Result>();
        expected.add(new Result(mockSection, classrooms.get(0), classrooms.get(1), new ArrayList<Classroom>(), new ArrayList<Classroom>(), 50));
        // Assert the result
        assertEquals(expected.toString(), result.toString());
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
        mockSchedule.updateCrosslist();

        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 65, 0, null, null, null));
        classrooms.add(new Classroom(3, 50, 0, null, null, null));
        classrooms.add(new Classroom(4, 100, 0, null, null, null));

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Invoke method under test
        ArrayList<Result> result = Algorithm.doAlgorithm(mockSection, 50, schedule, classroomList);

        ArrayList<Classroom> other = new ArrayList<Classroom>();
        other.add(classrooms.get(1));
        other.add(classrooms.get(3));

        ArrayList<Result> expected = new ArrayList<Result>();
        expected.add(new Result(mockSection, classrooms.get(0), classrooms.get(2), new ArrayList<Classroom>(), other, 50));
        
        
        // Assert the result
        assertEquals(expected.toString(), result.toString());
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
        mockSchedule.updateCrosslist();

        classrooms.add(new Classroom(1, 15, 0, null, null, null));
        classrooms.add(new Classroom(2, 45, 0, null, null, null));


        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Invoke method under test
        ArrayList<Result> result = Algorithm.doAlgorithm(mockSection, 50, schedule, classroomList);

        // Assert the result
        assertEquals(null, result);
    }

    @Test
    void testDoAlgorithm_recursive() {
        // Mock data
        ArrayList<Classroom> classrooms = new ArrayList<>();
        // Populate classrooms array with mock data
        ArrayList<DayOfWeek> meetingDays = new ArrayList<DayOfWeek>();
        meetingDays.add(DayOfWeek.MONDAY);
        LocalTime start = LocalTime.of(12, 0);
        LocalTime end = LocalTime.of(16,0);
        LocalTime start1 = LocalTime.of(12, 0);
        LocalTime end1 = LocalTime.of(14,0);
        LocalTime start2 = LocalTime.of(14, 30);
        LocalTime end2 = LocalTime.of(15,30);

        Schedule mockSchedule = new Schedule();
        Section mockSection = new Section("test", 1, "test", "", meetingDays, start, end, null, 1, null, null, 10, 40);
        Section mockSection1 = new Section("test1", 2, "test1", "", meetingDays, start1, end1, null, 2, null, null, 10, 40);
        Section mockSection2 = new Section("test2", 3, "test2", "", meetingDays, start2, end2, null, 3, null, null, 10, 40);
        mockSchedule.addSection(mockSection);
        mockSchedule.addSection(mockSection1);
        mockSchedule.addSection(mockSection2);
        mockSchedule.updateCrosslist();

        classrooms.add(new Classroom(1, 40, 0, null, null, null));
        classrooms.add(new Classroom(2, 40, 0, null, null, null));
        classrooms.add(new Classroom(3, 40, 0, null, null, null));

        classroomList.setClassrooms(classrooms);

        ClassroomList test = new ClassroomList();
        test.setClassrooms(classrooms);

        // Mock behavior
        when(schedule.returnSchedule()).thenReturn(mockSchedule.returnSchedule());
        when(classroomList.returnClassrooms()).thenReturn(classrooms);

        // Invoke method under test
        ArrayList<Result> result = Algorithm.doAlgorithm(mockSection, 40, mockSchedule, test);

        ArrayList<Result> expected = new ArrayList<Result>();
        expected.add(new Result(mockSection, classrooms.get(0), classrooms.get(1), new ArrayList<Classroom>(), new ArrayList<Classroom>(), 40));
        expected.add(new Result(mockSection1, classrooms.get(1), classrooms.get(2), new ArrayList<Classroom>(), new ArrayList<Classroom>(), 40));

        // Assert the result
        assertEquals(expected.toString(), result.toString());
    }
}
