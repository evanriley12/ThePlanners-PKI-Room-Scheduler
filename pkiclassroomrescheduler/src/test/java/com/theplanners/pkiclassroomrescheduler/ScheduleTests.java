package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;

@SpringBootTest
public class ScheduleTests {

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        schedule = new Schedule();
    }

    @Test
    public void testReturnCSSchedule() {
        Section csSection1 = mock(Section.class);
        when(csSection1.getCourse()).thenReturn("CSCI 101");
        Section csSection2 = mock(Section.class);
        when(csSection2.getCourse()).thenReturn("MATH 201");
        Section bioSection = mock(Section.class);
        when(bioSection.getCourse()).thenReturn("BIO 101");

        schedule.addSection(csSection1);
        schedule.addSection(csSection2);
        schedule.addSection(bioSection);

        ArrayList<Section> csSchedule = schedule.returnCSSchedule();

        assertEquals(2, csSchedule.size());
        assertTrue(csSchedule.contains(csSection1));
        assertTrue(csSchedule.contains(csSection2));
    }


    @Test
    public void testUpdateCrosslist() {
        Section section1 = mock(Section.class);
        Section section2 = mock(Section.class);
        ArrayList<Section> mockSchedule = new ArrayList<>();
        mockSchedule.add(section1);
        mockSchedule.add(section2);

        schedule.addSection(section1);
        schedule.addSection(section2);

        schedule.updateCrosslist();

        verify(section1).setCrossListMax(mockSchedule);
        verify(section2).setCrossListMax(mockSchedule);
    }
}
