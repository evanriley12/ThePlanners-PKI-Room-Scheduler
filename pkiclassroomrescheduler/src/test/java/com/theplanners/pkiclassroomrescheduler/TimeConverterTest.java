package com.theplanners.pkiclassroomrescheduler;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Utilites.MeetingTimeConverter;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.MeetingTimeConverter.MeetingTime;

@SpringBootTest
public class TimeConverterTest {

    @Test
    public void testParseMeetingTime_ValidFormat() {
        String meetingTimeString = "MW 3pm-4:15pm";
        MeetingTime meetingTime = MeetingTimeConverter.parseMeetingTime(meetingTimeString);

        assertNotNull(meetingTime);
        assertEquals(LocalTime.of(15, 0), meetingTime.getStartTime());
        assertEquals(LocalTime.of(16, 15), meetingTime.getEndTime());

        ArrayList<DayOfWeek> expectedDays = new ArrayList<>();
        expectedDays.add(DayOfWeek.MONDAY);
        expectedDays.add(DayOfWeek.WEDNESDAY);
        assertEquals(expectedDays, meetingTime.getDays());
    }

    @Test
    public void testParseMeetingTime_InvalidFormat() {
        String meetingTimeString = "Invalid meeting time format";
        MeetingTime meetingTime = MeetingTimeConverter.parseMeetingTime(meetingTimeString);

        assertNull(meetingTime);
    }

    @Test
    public void testConvertToDayOfWeek() {
        ArrayList<DayOfWeek> expectedDays = new ArrayList<>();
        expectedDays.add(DayOfWeek.MONDAY);
        expectedDays.add(DayOfWeek.WEDNESDAY);
        expectedDays.add(DayOfWeek.FRIDAY);

        ArrayList<DayOfWeek> convertedDays = MeetingTimeConverter.convertToDayOfWeek("MWF");

        assertEquals(expectedDays, convertedDays);
    }

    @Test
    public void testConvertToLocalTime() {
        LocalTime expectedTime = LocalTime.of(15, 0);

        LocalTime convertedTime = MeetingTimeConverter.convertToLocalTime("3pm");

        assertEquals(expectedTime, convertedTime);
    }
}
