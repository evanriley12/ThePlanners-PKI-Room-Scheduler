import React, { useState } from "react";
import axios from "axios";
import "./ClassroomReassignment.css";

let outputResultCounter = 1;
let cumulativeAlgoData = "";

/**
 * ClassroomReassignment is the function that is responsible for handling the frontend webpage
 * presentation for the PKI Classroom rescheduler. Constants for handling different frontend
 * activities are included as well as a return for the HTML used to create the webpage.
 * 
 * @returns An HTML webpage for users to interact with when rescheduling PKI classrooms.
 */
function ClassroomReassignment() {
  const [file, setFile] = useState(null);
  const [uploadSuccess, setUploadSuccess] = useState(false);
  const [csvData, setCsvData] = useState(null);
  const [algoData, setAlgoData] = useState(null);
  const baseURL = window.location.href;

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
    setUploadSuccess(false); // Reset upload success message when a new file is selected
  };

  const downloadFile = () => {
    const link = document.createElement("a");

    let content;
    if (cumulativeAlgoData !== "") {
      content = cumulativeAlgoData;
    }
    else {
      content = "No rescheduler results are available at this time.";
    }

    const file = new Blob([content], {type: 'text/plain'});
    link.href = URL.createObjectURL(file);
    link.download = "results.txt"
    link.click();
    URL.revokeObjectURL(link.href);
  }

  const handleUpload = async () => {
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await axios.post(
        baseURL+"api/upload",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log("File uploaded successfully");
      setUploadSuccess(true);
      setCsvData(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Error uploading file:", error);
    };
  };

  const runAlgorithm = async () => {
    try {

      // Remove the leading 0s before the section number before passing to Java
      let selectedClass = document.getElementById("courseSelect").value;
      let selectedClassNoPadding = selectedClass.replace(/Section 0+/, "Section ");
      console.log(selectedClassNoPadding);

      // If the default action is chosen, the reschedule button won't do anything
      if (selectedClass != "Choose Class") {
        let newMaxEnrollment = document.getElementById("maxClassSize").value;

        const response = await axios.get(baseURL + "api/algorithm", {
          params: {classSection: selectedClassNoPadding, newSize: newMaxEnrollment},
        });
        console.log("Algorithm executed successfully");
        
        setAlgoData(response.data);
        console.log(response.data);
      }
    }
    catch (error) {
      console.error("Error executing algorithm:", error);
    }
  };

  const dropDownSelection = (event) => {
    // Log that a selection has changed
    console.log("Selection Changed!");

    // Check the updated value from the dropdown box selection change
    let selectedCourse = event.target.value;
    console.log(selectedCourse);

    // Class informational Labels with groupings by return line
    let courseAndSection = "Course/Section: ";
    let title = "\nTitle: ";
    let crosslisted = "\nCrosslisted Courses: ";
    let instructor = "\n\nInstructor: ";
    let sectionType = "\nSection Type: ";
    let instructionMethod = "\nInstruction Method: ";
    let meetingDays = "\n\nMeeting Days: ";
    let roomNumber = "\nRoom Number: ";
    let startTime = "\nStart Time: ";
    let endTime = "\nEnd Time: ";
    let maxClassSize = "\n\nMax Class Size: ";
    let enrollment = "\nEnrollment: ";
    let crosslistsize = "\nCrosslist Max Size: ";

    if(csvData) {
      csvData.every((element) => {

        // Pad all sections in need of leading 0s (e.g. 001, 002, etc.)
        let sectionNumberString = element["sectionNumber"].toString();
        let paddedSectionNumberString = sectionNumberString.padStart(3, '0');

        // Grab the course from an element iteration in the same format of the dropdown selection
        let currentCourseIteration = element["course"] + " - Section " + paddedSectionNumberString;

        // Check if CSV entry is a match for the dropdown selection
        if (selectedCourse === currentCourseIteration) {
          console.log("We have a match!");

          // Separate only the instructor name for display purposes
          let instructorPart = element["instructor"].split(" (", 1);
          if (element["instructor"].includes("Staff"))
          {
            instructorPart = element["instructor"].split(" [", 1);
          }

          // Separate meeting days with a ", " for matching formatting
          let meetingDaysString = element["meetingDays"].toString();
          let fixedMeetingDaysString = meetingDaysString.split(",").join(", ");
        
          // Check whether there is a cross listed course, and print "None" if so
          let crossListedEntry = element["crossList"];
          if (!crossListedEntry) {
            crossListedEntry = "None";
          }

          // Fill Text Area with Class Information
          let chosenClassInformation = courseAndSection + currentCourseIteration
          + title + element["courseTitle"]
          + crosslisted + crossListedEntry

          + instructor + instructorPart
          + sectionType + element["sectionType"]
          + instructionMethod + element["instructionMethod"]

          + meetingDays + fixedMeetingDaysString
          + roomNumber + element["roomNumber"]
          + startTime + element["startTime"]
          + endTime + element["endTime"]
          
          + maxClassSize + element["maxEnrollment"]
          + enrollment + element["enrollment"]
          + crosslistsize + element["crossListMax"];
          
          document.getElementById("classInfoDisplay").value = chosenClassInformation;

          // Set the values of the number boxes for max class size and enrollment
          document.getElementById("maxClassSize").value = element["crossListMax"];
          document.getElementById("enrollmentSize").value = element["enrollment"];

          document.getElementById("maxClassSize").min = element["crossListMax"];
          document.getElementById("enrollmentSize").min = element["enrollment"];

          // Break out of the loop once match has been found
          return false;
        }

        return true;
      })
    }
  };

  const createNewOutputWidget = (text) => {

    // Locate the div representing the body of the results column where output widgets are to be placed
    let resultsColumnDiv = document.getElementById("resultsColumn");

    // If there have been no outputs generated previously, clear the placeholder text before placing a result
    if (resultsColumnDiv.innerHTML == "No results available. Please upload a schedule and select a course."
        || resultsColumnDiv.innerHTML == "Rescheduler results cleared.") {
      resultsColumnDiv.innerHTML = "";
    }
    
    // Create a new output result and append it to the column containing rescheduler results
    if (text) {
      // Add the text to the variable that is used to cumulatively store info to be printed to the results file.
      cumulativeAlgoData += ("Output Result #" + outputResultCounter + ": \n" + text + "\n\n");

      let newDiv = document.createElement("div");
      newDiv.className = "ClassroomReassignment-outputWidget";
      newDiv.innerHTML = "Output Result #" + outputResultCounter + ": \n" + text;
      resultsColumnDiv.prepend(newDiv);
      outputResultCounter++;
    }
  };

  const clearReschedulerResults = (event) => {

    // Locate the div representing the body of the results column where output widgets have been placed
    let resultsColumnDiv = document.getElementById("resultsColumn");

    // Clear all rescheduler output results, no changes will occur if no results are present
    if (resultsColumnDiv.innerHTML !== "No results available. Please upload a schedule and select a course."
        && resultsColumnDiv.innerHTML !== "Rescheduler results cleared.") {
      resultsColumnDiv.innerHTML = "Rescheduler results cleared.";
      outputResultCounter = 1;
      cumulativeAlgoData = "";
    }
  }

  return (
    <div className="ClassroomReassignment-page">
      <div className="ClassroomReassignment-workingColumn">
        <header className="ClassroomReassignment-workingHeader">
          PKI Classroom Rescheduler
        </header>
        <div className="ClassroomReassignment-inputColumn">
          <form>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-fileSelectionButtonDiv">
                <input type="file" onChange={handleFileChange}/>
              </div>
              <div className="ClassroomReassignment-fileSelectionButtonDiv">
                <button type="button" onClick={handleUpload}
                className="ClassroomReassignment-fileSelectionButton">
                  Upload CSV
                </button>
                {uploadSuccess && <p>Upload successful!</p>}
              </div>
            </div>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-fileUploadButton">
                <select className="ClassroomReassignment-dropdownBox" id="courseSelect" onChange={dropDownSelection}>
                  <option selected>Choose Class</option>
                </select>
                  {
                    csvData && (
                      // Populate Dropdown box
                      csvData.forEach((element) => {
                        let courseSelect = document.getElementById("courseSelect");
                        let course = document.createElement("option");

                        let sectionNumberString = element["sectionNumber"].toString();
                        let paddedSectionNumberString = sectionNumberString.padStart(3, '0');

                        course.text = element["course"] + " - Section " + paddedSectionNumberString;
                        course.value = element["course"] + " - Section " + paddedSectionNumberString;
                        courseSelect.appendChild(course);
                      })
                    )
                  }
              </div>
            </div>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-numberBox">
                <label
                  htmlFor="maxClassSize"
                  className="ClassroomResassignment-selectBoxLabel"
                >
                  Max Class Size:{" "}
                </label>
                <input
                  className="ClassroomResassignment-selectBox"
                  type="number"
                  id="maxClassSize"
                  name="maxClassSize"
                  step="1"
                ></input>
              </div>
            </div>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-numberBox">
                <label
                  htmlFor="enrollmentSize"
                  className="ClassroomResassignment-selectBoxLabel"
                >
                  Enrollment:{" "}
                </label>
                <input
                  className="ClassroomResassignment-selectBox"
                  type="number"
                  id="enrollmentSize"
                  name="enrollmentSize"
                  step="1"
                ></input>
              </div>
            </div>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-fileUploadButton">
                <button
                  type="button"
                  className="ClassroomReassignment-rescheduleButton"
                  onClick={runAlgorithm}
                >
                  Reschedule
                </button>
              </div>
            </div>
          </form>
        </div>
        <div className="ClassroomReassignment-displayColumn">
          <textarea id="classInfoDisplay" rows="10" cols="10" readOnly>
            No course information available. Please upload a schedule and select a course to view its corresponding information.
          </textarea>
        </div>
      </div>
      <div className="ClassroomReassignment-resultsColumn">
        <header className="ClassroomReassignment-resultsHeader">
          <div className="ClassroomReassignment-resultsLeftSpacer">
          <button 
            type="button"
            className="ClassroomReassignment-clearResultsButton"
            onClick={downloadFile}>
            Download Results</button>
          </div>
          <div className="ClassroomReassignment-resultsLabel">Results</div>
          <div className="ClassroomReassignment-clearResultsButtonDiv">
            <button 
              className="ClassroomReassignment-clearResultsButton"
              onClick={clearReschedulerResults}
            >
              Clear Results
            </button>
          </div>
        </header>
        <div className="ClassroomReassignment-resultsColumnBody" id="resultsColumn">
          No results available. Please upload a schedule and select a course.
          {algoData && createNewOutputWidget(algoData)}
        </div>
      </div>
    </div>
  );
}

export default ClassroomReassignment;
