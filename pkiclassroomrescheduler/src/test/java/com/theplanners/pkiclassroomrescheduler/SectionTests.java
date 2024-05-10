package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;

@SpringBootTest
public class SectionTests {

    @Test
    public void testSetCrossListMax() {
        Section section1 = new Section("CSCI 101", 1, "Introduction to Computer Science", "Lecture",
                new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY)), LocalTime.of(9, 0),
                LocalTime.of(10, 30), "John Doe", 101, "In person", "", 30, 100);
        Section section2 = new Section("CSCI 101", 2, "Introduction to Computer Science", "Lab",
                new ArrayList<>(Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)), LocalTime.of(13, 0),
                LocalTime.of(14, 30), "Jane Smith", 102, "Online", "", 20, 50);

        // Add cross listed sections
        section1.addCrossList("CSCI 101-002");
        section2.addCrossList("CSCI 101-001");

        // Create a schedule
        ArrayList<Section> schedule = new ArrayList<>(Arrays.asList(section1, section2));

        // Calculate cross list max
        section1.setCrossListMax(schedule);

        // Check if cross list max is calculated correctly
        assertEquals(150, section1.getCrossListMax());
    }

    @Test
    public void testOverlaps() {
        ArrayList<DayOfWeek> meetingDays = new ArrayList<DayOfWeek>();
        meetingDays.add(DayOfWeek.MONDAY);
        LocalTime start = LocalTime.of(12, 0);
        LocalTime end = LocalTime.of(16,0);
        LocalTime start1 = LocalTime.of(12, 0);
        LocalTime end1 = LocalTime.of(14,0);
        LocalTime start2 = LocalTime.of(14, 30);
        LocalTime end2 = LocalTime.of(15,30);

        Section mockSection = new Section("test", 1, "test", "", meetingDays, start, end, null, 1, null, null, 10, 40);
        Section mockSection1 = new Section("test1", 2, "test1", "", meetingDays, start1, end1, null, 2, null, null, 10, 40);
        Section mockSection2 = new Section("test2", 3, "test2", "", meetingDays, start2, end2, null, 3, null, null, 10, 40);

        // Check if sections overlap
        assertTrue(mockSection.Overlaps(mockSection1)); // Lecture and Lab overlap
        assertFalse(mockSection1.Overlaps(mockSection2)); // Lecture and Lecture do not overlap
    }
}

