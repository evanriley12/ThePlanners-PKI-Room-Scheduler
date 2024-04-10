package com.theplanners.pkiclassroomrescheduler.system.Utilites;

import java.util.ArrayList;
import java.util.regex.*;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
/**
 * MeetingTimeConverter is a class that converts the meeting time string (Ex: "MW 3pm-4:15pm")
 * into easier to use formats, an ArrayList<DayOfWeek> for meeting days and a LocalTime for 
 * each the start and end time.
 */
public final class MeetingTimeConverter {
    /**
     * Takes a meeting time string, formatted as "MW 3pm-4:15pm", parses out the meeting days and 
     * meeting times, and returns tham as a MeetingTime object.
     * @param meetingTimeString String directly from csv in format "MW 3pm-4:15pm"
     * @return MeetingTime: a custom class that contains the meeting days, the start time, and the end time.
     *         or returns null if pattern doesn't match.
     */
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

    /**
     * Given a string, formatted "MW" or "F" representing Monday, Wednesday and Friday respectively,
     * return an ArrayList of DayOfWeek enumerations
     * Ex:
     *  "MW" -> [MONDAY, WEDNESDAY]
     * @param daysString the string containing the meeting days
     * @return ArrayList of DayOfWeek enumerations representing the input string
     * 
     * @see java.time.DayOfWeek
     */
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

    /**
     * Takes a time in the format "3pm" or "8am" and converts it to java.LocalTime
     * @param timeString the string containing the a time in format "3pm"
     * @return the time represented as a LocalTime
     * 
     * @see java.LocalTime
     */
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

    /**
     * MeetingTime class is designed to represent the meeting time of a class, both days and times 
     * in an easier to use format than a string
     * 
     * @see java.LocalTime
     * @see java.DayOfWeek
     */
    public static class MeetingTime {
        private ArrayList<DayOfWeek> days;
        private LocalTime startTime;
        private LocalTime endTime;

        public MeetingTime(ArrayList<DayOfWeek> days, LocalTime startTime, LocalTime endTime) {
            this.days = days;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public ArrayList<DayOfWeek> getDays() {
            return days;
        }

        public void setDays(ArrayList<DayOfWeek> days) {
            this.days = days;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }
    }
}