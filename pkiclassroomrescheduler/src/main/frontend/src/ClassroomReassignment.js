import React, { useState } from "react";
import axios from "axios";
import "./ClassroomReassignment.css";

function ClassroomReassignment() {
  const [file, setFile] = useState(null);
  const [uploadSuccess, setUploadSuccess] = useState(false);
  const [csvData, setCsvData] = useState(null);

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
    setUploadSuccess(false); // Reset upload success message when a new file is selected
  };

  const handleUpload = async () => {
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/upload",
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
    } catch (error) {
      console.error("Error uploading file:", error);
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
                <select className="ClassroomReassignment-dropdownBox">
                  <option selected>Choose Class</option>
                </select>
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
        {csvData && (
          <table>
            <tbody>
              {csvData.map((row, rowIndex) => (
                <tr key={rowIndex}>
                  {row.map((cell, cellIndex) => (
                    <td key={cellIndex}>{cell}</td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default ClassroomReassignment;
