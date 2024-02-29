import React, { useState } from 'react';
import axios from 'axios';
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
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8080/api/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      console.log('File uploaded successfully');
      setUploadSuccess(true);
      setCsvData(response.data);
    } catch (error) {
      console.error('Error uploading file:', error);
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
            <input type="file" onChange={handleFileChange} />
            <button type="button" onClick={handleUpload}>Upload CSV</button>
            {uploadSuccess && <p className="successMessage">Upload successful!</p>}
            <p padding="15px">Setup file display here...</p>
            <select>
              <option selected>Choose Class</option>
            </select>
            <br></br>
            <label htmlFor="maxClassSize">Max Class Size </label>
            <input
              type="number"
              id="maxClassSize"
              name="maxClassSize"
              step="1"
              min="0"
            ></input>
            <br></br>
            <label htmlFor="enrollmentSize">Enrollment </label>
            <input
              type="number"
              id="enrollmentSize"
              name="enrollmentSize"
              step="1"
              min="0"
            ></input>
            <br></br>
            <button type="button">Reschedule</button>
          </form>
        </div>
        <div className="ClassroomReassignment-displayColumn">
          <textarea rows="10" cols="10">
            Display class information here...
          </textarea>
        </div>
      </div>
      <div className="ClassroomReassignment-resultsColumn">
        <header className="ClassroomReassignment-resultsHeader">
            <div className="resultsLabel">
                Results
            </div>
            <div className="clearResultsButton">
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
