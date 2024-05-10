# PKI Room Scheduler

UNO - Spring 2024 CS Capstone Project

Our application is a tool to manage and reschedule classroom assignments for PKI.

The main branch of this project is the latest version

Backend Docs: https://evanriley12.github.io/ThePlanners-PKI-Room-Scheduler/

Frontend Docs: https://evanriley12.github.io/Rescheduler-Frontend-Docs/index.html

# Usage instructions:
- This project is designed to be used with the auto generated schedule csv files from the registrar
- Access the deployed application on a web browser
- Select a file (The csv containing the schedule that needs classes moved)
- Upload the file, this will populate the dropdown with all of the sections that can be resechduled
- Once a section is selected the max class size can be adjusted (This number will reflect the combined size of any crosslisted courses). It is not required to change the max size, this will then just find a different room at the same size
- Hit the reschedule button:
    - This will output the results in the left panel.
    - If multiple classes need to be moved to accommodate the change they will show up as seperate widgets
    - Each widget displays the room it was in and the optimal room to move it to, if one exists. 
    - It will select the smallest possible room that satisfies the max class size
    - It will also display a list of other optimal rooms
    - Lastly it will display a list of suitable but suboptimal rooms. 
- Multiple courses can be rescheduled and results will stack
- The download results button will download a text file of all the current results
- The clear button will clear all results and reset the schedule to the state it was in when originally uploaded.

# Build Instructions: Note: Maven is required to build this project
- With Maven, clean then build the project. This will output a war into the target directory that can be deployed

# Deployment instructions
- Once the war is generated, it can be deployed to a tomcat server.
- Tomcat 10 is required for the project to deploy successfully

# Milestone 4 Release Notes:
- New Features:
    - The Algorithm now outputs a Result object that is easier to work with on the backend, and also results in some more detailed information in the output.
    - Error handling added in a few rare cases where classes cannot be rescheduled.
    - The algorithm output display has been revamped to allow for multiple results to be displayed at once.
    - The algorithm output display window is now scrollable to allow the user to stack as many outputs as needed.
    - Added a download results button that outputs a text file containing all information in the result field to allow for a way to save results.
    - Online courses no longer display in the course selection box, since they don't have a classroom to begin with.
    - Added Javadocs via Doxygen to give information about all of our classes.
    - Various visual changes to the frontend to add more polish.
    - Various general bug fixes.

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

# Milestone 2 Release Notes:
- Polished the frontend for a smoother and more aesthetically pleasing experience.
- The user is now notified when an upload of the csv succeeds or fails.
- The application now parses data from the uploaded csv for use in the backend (can also be seen in the dropdown menu).
- Implemented a very basic algorithm that displays other classrooms that could fit a selected course.
    - Due to some issues with the dropdown box, this is always showing the algorithm being executed on the first class in the list with a new enrollment of 50.
- Lots of backend work to prepare for more in-depth algorithm development.
- Added basic tests to handle uploading and parsing the csv file.

# Milestone 1 Release Notes:
- Connected front and backend (React and Springboot).
- Implemented a basic frontend to represent what the application will look like with the general layout based on our wireframe.
- Implemented a file upload button that reads in a csv and displays contents, this was originally planned to be stored as an object, but for now it just displays the text. Parsing and storing data will be implemented once work on the algorithm begins.