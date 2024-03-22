import React, { useState } from "react";
import axios from "axios";
import "./ClassroomReassignment.css";

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
      const response = await axios.get(baseURL + "api/algorithm", {
        params: { classSection: "AREN 1030 - Section 1", newSize: 50 },
      });
      console.log("Algorithm executed successfully");
      setAlgoData(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Error executing algorithm:", error);
    }
  };

  return (
    <div className="ClassroomReassignment-page">
      <div className="ClassroomReassignment-workingColumn">
        <header className="ClassroomReassignment-workingHeader">
          PKI Classroom Rescheduler
        </header>
        <div className="ClassroomReassignment-inputColumn">
          <form>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-fileSelectionButton">
                <input type="file" onChange={handleFileChange} />
              </div>
              <div className="ClassroomReassignment-fileSelectionButton">
                <button type="button" onClick={handleUpload}>
                  Upload CSV
                </button>
                {uploadSuccess && <p>Upload successful!</p>}
              </div>
            </div>
            <div className="ClassroomReassignment-individualInput">
              <div className="ClassroomReassignment-fileUploadButton">
                <select className="ClassroomReassignment-dropdownBox" id="courseSelect">
                  <option selected>Choose Class</option>
                </select>
                  {
                    csvData && (
                      // Populate Dropdown box
                      csvData.forEach((element) => {
                        let courseSelect = document.getElementById("courseSelect");
                        let course = document.createElement("option");
                        course.text = element["course"] + " - Section " + element["sectionNumber"];
                        course.value = element["course"] + " - Section " + element["sectionNumber"];
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
                  value="0"
                  step="1"
                  min="0"
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
                  value="0"
                  step="1"
                  min="0"
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
          <textarea rows="10" cols="10" readOnly>
            Display class information here...
          </textarea>
        </div>
      </div>
      <div className="ClassroomReassignment-resultsColumn">
        <header className="ClassroomReassignment-resultsHeader">
          <div className="ClassroomReassignment-resultsLeftSpacer"></div>
          <div className="ClassroomReassignment-resultsLabel">Results</div>
          <div className="ClassroomReassignment-clearResultsButton">
            <button>Clear Results</button>
          </div>
        </header>
        {algoData ? JSON.stringify(algoData) : 'No results available'}
      </div>
    </div>
  );
}

export default ClassroomReassignment;
