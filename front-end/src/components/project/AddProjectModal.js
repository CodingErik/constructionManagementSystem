import { useRef, useState } from 'react';

function AddProjectModal({ modalId, handleNewProjectSubmit, hasAuthority }) {
  const nameRef = useRef();
  const startDateRef = useRef();
  const deadlineRef = useRef();
  const roomTypeRef = useRef();
  const laborBudgetRef = useRef();
  const materialBudgetRef = useRef();
  const totalBudgetRef = useRef();
  const isPlumbingRef = useRef();
  const isElectricRef = useRef();
  const [projectState, setProjectState] = useState('in_progress');

  const handleSubmit = (event) => {
    event.preventDefault();
    const newProjectInfo = {
      name: nameRef.current.value,
      status: projectState,
      startDate: startDateRef.current.value,
      deadline: deadlineRef.current.value,
      roomType: roomTypeRef.current.value,
      laborBudget: laborBudgetRef.current.value
        ? laborBudgetRef.current.value
        : 0,
      materialBudget: materialBudgetRef.current.value
        ? materialBudgetRef.current.value
        : 0,
      totalBudget: totalBudgetRef.current.value
        ? totalBudgetRef.current.value
        : 0,
      isPlumbing: isPlumbingRef.current.checked,
      isElectric: isElectricRef.current.checked,
    };
    handleNewProjectSubmit(newProjectInfo);
    // console.log(newProjectInfo);
  };

  return (
    <div className='container'>
      <div
        className='modal fade'
        id={modalId}
        tabIndex='-1'
        aria-labelledby='addProjectModal'
        aria-hidden='true'
      >
        <div className='modal-dialog modal-lg'>
          <div className='modal-content'>
            <div className='modal-header'>
              <h5 className='modal-title'>Add Project</h5>
              <button
                type='button'
                className='btn-close'
                data-bs-dismiss='modal'
                aria-label='Close'
              ></button>
            </div>
            <div className='row'>
              <div className="modal-body">
                <form style={{ margin: "0 auto" }} onSubmit={(event) => handleSubmit(event)}>
                  <fieldset>
                    <div className="form-group">
                      <label
                        htmlFor="projectTitle"
                        className="form-label mt-4 ms-4 d-flex align-items-start"
                      >
                        New Project
                      </label>
                      <input
                        className="form-control ms-4"
                        id="projectTitle"
                        readOnly={!hasAuthority}
                        ref={nameRef}
                        required
                      />
                    </div>
                    <div className="form-group">
                      <label
                        htmlFor="status"
                        className="form-label mt-4 ms-4 d-flex align-items-start"
                      >
                        Status
                      </label>
                      <select
                        className="form-select ms-4"
                        id="status"
                        disabled={!hasAuthority}
                        value={projectState}
                        onChange={(event) => setProjectState(event.target.value)}
                      >
                        <option value="in_progress" >In Progress</option>
                        <option value="completed" >Completed</option>
                        <option value="cancelled" >Cancelled</option>
                      </select>
                    </div>
                    <div className="form-group">
                      <div className="form-group row">
                        <label htmlFor="startDate" className="col-sm-2 col-form-label">
                          Start Date
                        </label>
                        <div className="col-sm-4">
                          <input
                            type="date"
                            readOnly={!hasAuthority}
                            className="form-control-plaintext"
                            id="startDate"
                            ref={startDateRef}
                            required
                          />
                        </div>
                        <label htmlFor="deadline" className="col-sm-2 col-form-label">
                          Deadline
                        </label>
                        <div className="col-sm-4">
                          <input
                            type="date"
                            readOnly={!hasAuthority}
                            className="form-control-plaintext"
                            id="deadline"
                            ref={deadlineRef}
                            required
                          />
                        </div>
                      </div>
                    </div>
                    <div className="form-group">
                      <label
                        htmlFor="roomType"
                        className="form-label mt-4 ms-4 d-flex align-items-start"
                      >
                        Room Type
                      </label>
                      <input
                        className="form-control ms-4"
                        id="roomType"
                        readOnly={!hasAuthority}
                        ref={roomTypeRef}
                      />
                    </div>
                    <div className="form-group" id="budgetsFormGroup">
                      <div className="form-group row">
                        <label htmlFor="laborBudget" className="col-lg-1 col-form-label">
                          Labor Budget
                        </label>
                        <div className="col-lg-3" style={{ display: "inline" }}>
                          <span className="input-group-text" style={{ display: "inline" }}>
                            $
                          </span>
                          <input
                            type="number"
                            readOnly={!hasAuthority}
                            className="form-control-plaintext"
                            style={{ display: "inline", width: "20%" }}
                            id="laborBudget"
                            ref={laborBudgetRef}
                          />
                        </div>
                        <label htmlFor="laborBudget" className="col-lg-1 col-form-label">
                          Material Budget
                        </label>
                        <div className="col-lg-3" style={{ display: "inline" }}>
                          <span className="input-group-text" style={{ display: "inline" }}>
                            $
                          </span>
                          <input
                            type="number"
                            readOnly={!hasAuthority}
                            className="form-control-plaintext"
                            style={{ display: "inline", width: "20%" }}
                            id="materialBudget"
                            ref={materialBudgetRef}
                          />
                        </div>
                        <label htmlFor="laborBudget" className="col-lg-1 col-form-label">
                          Total Budget
                        </label>
                        <div className="col-lg-3" style={{ display: "inline" }}>
                          <span className="input-group-text" style={{ display: "inline" }}>
                            $
                          </span>
                          <input
                            type="number"
                            readOnly={!hasAuthority}
                            className="form-control-plaintext"
                            style={{ display: "inline", width: "20%" }}
                            id="totalBudget"
                            ref={totalBudgetRef}
                          />
                        </div>
                      </div>
                    </div>
                    <div className="form-check form-switch text-start">
                      <label className="form-check-label " htmlFor="isPlumbing">
                        Has Plumbing
                      </label>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id="isPlumbing"
                        ref={isPlumbingRef}
                        disabled={!hasAuthority}
                      />
                    </div>
                    <div className="form-check form-switch text-start">
                      <label className="form-check-label " htmlFor="isElectric">
                        Has Electric
                      </label>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id="isElectric"
                        ref={isElectricRef}
                        disabled={!hasAuthority}
                      />
                    </div>
                  </fieldset>
                  <button className="btn btn-info" type="submit" disabled={!hasAuthority}>
                    Add Project
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AddProjectModal;
