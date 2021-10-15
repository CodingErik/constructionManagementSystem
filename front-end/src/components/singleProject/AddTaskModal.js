import TaskForm from "../singleTask/TaskForm";
import { useRef, useState } from "react";


function AddTaskModal({ modalId, projectId, handleAddTaskToProject, hasAuthority, availableEmployeesInProject, projectName }) {
    const nameRef = useRef(null);
    const startDateRef = useRef(null);
    const deadlineRef = useRef(null);
    const descriptionRef = useRef(null);
    const [employeeState, setEmployeeState] = useState(availableEmployeesInProject[0]?.id);
    const [taskState, setTaskState] = useState("in_progress");
    const [projectState, setProjectState] = useState(projectId);

    const handleSubmitForm = (event) => {
        event.preventDefault();
        const newTask = {
            name: nameRef.current.value,
            projectId: projectState,
            employeeId: employeeState ? employeeState : availableEmployeesInProject[0]?.id,
            startDate: startDateRef.current.value,
            deadline: deadlineRef.current.value,
            description: descriptionRef.current.value,
            status: taskState
        };
        console.log(newTask);
        if(availableEmployeesInProject.length > 0) {
             handleAddTaskToProject(newTask);
        } else {
            alert("A Employee Must Exist In Project Before Task Can Be Created")
        }
    }

    return (
        <div>
            <div className="modal fade" id={modalId} tabIndex="-1" aria-labelledby="addModal" aria-hidden="true">
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add Task</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form style={{ margin: "0 auto" }} onSubmit={(event) => handleSubmitForm(event)}>
                                <fieldset>
                                    <div className="form-group">
                                        <label
                                            htmlFor="taskTitle"
                                            className="form-label mt-4 ms-4 d-flex align-items-start"
                                        >
                                            Name:
                                        </label>
                                        <input
                                            className="form-control ms-4"
                                            id="taskTitle"
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
                                            value={taskState}
                                            onChange={(event) => setTaskState(event.target.value)}
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
                                            htmlFor="project"
                                            className="form-label mt-4 ms-4 d-flex align-items-start"
                                        >
                                            Project Containing This Task
                                        </label>
                                        <select
                                            className="form-select ms-4"
                                            id="project"
                                            value={projectState}
                                            onChange={(event) => setProjectState(event.target.value)}
                                            disabled={true}
                                        >
                                            <option value={projectId}>{projectId}. {projectName}</option>
                                        </select>
                                    </div>
                                    <div className="form-group">
                                        <label
                                            htmlFor="employee"
                                            className="form-label mt-4 ms-4 d-flex align-items-start"
                                        >
                                            Designate Employee For This Task
                                        </label>
                                        <select
                                            className="form-select ms-4"
                                            id="employee"
                                            disabled={!hasAuthority}
                                            value={employeeState}
                                            onChange={(event) => setEmployeeState(event.target.value)}
                                        >
                                            {availableEmployeesInProject.map(employee => (
                                                <option value={employee.id} key={employee.id}>{employee.id}. {employee.name}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className="form-group">
                                        <label
                                            htmlFor="description"
                                            className="form-label mt-4 ms-4 d-flex align-items-start"
                                        >
                                            Description
                                        </label>
                                        <textarea disabled={!hasAuthority} className="form-control ms-4" id="description" rows="8" ref={descriptionRef}></textarea>
                                    </div>

                                </fieldset>
                                <button className="btn btn-info" type="submit" disabled={!hasAuthority}>
                                    Apply Changes
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddTaskModal;