package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;
import java.util.regex.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class MeetingTimeConverter {
    public static MeetingTime parseMeetingTime(String meetingTimeString) {
        // Define the regex pattern to match the meeting time format
        Pattern pattern = Pattern.compile("([A-Za-z]+) (\\d{1,2}(?::\\d{2})?(?:am|pm))-(\\d{1,2}(?::\\d{2})?(?:am|pm))");
        Matcher matcher = pattern.matcher(meetingTimeString);

        if (matcher.find()) {
            ArrayList<DayOfWeek> days = convertToDayOfWeek(matcher.group(1));
            String startTimeString = matcher.group(2);
            String endTimeString = matcher.group(3);

            // Convert the time strings to LocalTime objects
            LocalTime startTime = convertToLocalTime(startTimeString);
            LocalTime endTime = convertToLocalTime(endTimeString);

            return new MeetingTime(days, startTime, endTime);
        }

        return null; // Return null if the format doesn't match
    }

    public static ArrayList<DayOfWeek> convertToDayOfWeek(String daysString) {
        ArrayList<DayOfWeek> days = new ArrayList<>();
        for (char c : daysString.toCharArray()) {
            switch (c) {
                case 'S':
                    days.add(DayOfWeek.SUNDAY);
                    break;
                case 'M':
                    days.add(DayOfWeek.MONDAY);
                    break;
                case 'T':
                    days.add(DayOfWeek.TUESDAY);
                    break;
                case 'W':
                    days.add(DayOfWeek.WEDNESDAY);
                    break;
                case 'R':
                    days.add(DayOfWeek.THURSDAY);
                    break;
                case 'F':
                    days.add(DayOfWeek.FRIDAY);
                    break;
                case 'A':
                    days.add(DayOfWeek.SATURDAY);
                    break;
            }
        }
        return days;
    }

    public static LocalTime convertToLocalTime(String timeString) {
        String[] parts = timeString.split("[:]");
        int hour = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
        int minute = parts.length > 1 ? Integer.parseInt(parts[1].replaceAll("[^0-9]", "")) : 0;
        if (timeString.contains("pm") && hour != 12) {
            hour += 12;
        } else if (timeString.contains("am") && hour == 12) {
            hour = 0;
        }
        return LocalTime.of(hour, minute);
    }

    static class MeetingTime {
        ArrayList<DayOfWeek> days;
        LocalTime startTime;
        LocalTime endTime;

        public MeetingTime(ArrayList<DayOfWeek> days, LocalTime startTime, LocalTime endTime) {
            this.days = days;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}