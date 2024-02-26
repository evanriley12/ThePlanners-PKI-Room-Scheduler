import "./ClassroomReassignment.css";

function ClassroomReassignment() {
  return (
    <div className="ClassroomReassignment-page">
      <div className="ClassroomReassignment-workingColumn">
        <header className="ClassroomReassignment-workingHeader">
          PKI Classroom Rescheduler
        </header>
        <div className="ClassroomReassignment-inputColumn">
          <form>
            <button type="button">Upload CSV</button>
            <p padding="15px">Setup file display here...</p>
            <select>
              <option selected>Choose Class</option>
            </select>
            <br></br>
            <label for="maxClassSize">Max Class Size </label>
            <input
              type="number"
              id="maxClassSize"
              name="maxClassSize"
              step="1"
              min="0"
            ></input>
            <br></br>
            <label for="enrollmentSize">Enrollment </label>
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
        <p>Eventually display reassignment results here...</p>
      </div>
    </div>
  );
}

export default ClassroomReassignment;
