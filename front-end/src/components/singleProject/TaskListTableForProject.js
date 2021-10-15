import { useEffect, useState } from "react";
import { EmployeeAPI, TaskAPI } from "../../api";
import AddTaskModal from "./AddTaskModal";

function TaskListTableForProject({ projectId, projectName, hasAuthority }) {
    const [taskList, setTaskList] = useState([]);
    const [availableEmployeesInProject, setAvailableEmployeesInProject] = useState([]);
    useEffect(() => {
        TaskAPI.getTaskByProjectId(projectId).then((response) => {
            setTaskList(response.data);
        })
        EmployeeAPI.getAllEmployeeByProjectId(projectId).then((response) => {
            setAvailableEmployeesInProject(response.data);
        })
    }, [])

    const handleAddTaskToProject = (newTask) => {
        TaskAPI.addTask(newTask);
        TaskAPI.getTaskByProjectId(projectId).then(response => {
            console.log(response.data)
            // setTaskList(response.data)
        })
    }

    return (
        <div>
            <h3>Tasks</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Task Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    {taskList.map(task => (
                        <tr className="table-active" key={task.id}>
                            <th scope="row">{task.id}</th>
                            <td>{task.name}</td>
                            <td>{task.status}</td>
                        </tr>
                    ))}
                    {hasAuthority && (
                        <tr>
                            <td>
                                <button type="button" className="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addTaskModal" disabled={!hasAuthority}>Add Task</button>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
            <AddTaskModal modalId="addTaskModal" availableEmployeesInProject={availableEmployeesInProject} projectId={projectId} projectName={projectName} handleAddTaskToProject={handleAddTaskToProject} hasAuthority={hasAuthority} />
        </div>
    )
}

export default TaskListTableForProject;