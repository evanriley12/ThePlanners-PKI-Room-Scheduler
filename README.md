# PKI Room Scheduler

UNO - Spring 2024 CS Capstone Project

Our application is a tool to manage and reschedule classroom assignments for PKI.

# Milestone 3 Release Notes:
- New Features:
    - The class information box now shows the class information for the section selected from the dropdown menu.
    - The Max Class Size and Enrollment input fields now automatically populate based on the selected section.
    - The Max Class Size and Enrollment input fields can now properly be incremented and decremented using the arrows in each box.
    - The Scheduling algorithm now actually works:
        - Determines the relationship between all classes to determine which have overlapping times.
        - Only considers rooms that are open during the time of the class.
        - Displays the best choice to move the room to, based on which available classroom has the smallest number of seats that still accomodate the change.
        - Also displays the other classrooms that would work just as well, as well as the options that work worse.

- Polish Changes:
    - Gave the application an actual title rather than using React's default.
    - Added a UNO icon to the tab display rather than using React's default.
    - Font and text size changes to make information more readable.
    - The class dropdown menu now adds leading 0's to the section number to make the line length more consistent.

Our Main branch has our most updated version.