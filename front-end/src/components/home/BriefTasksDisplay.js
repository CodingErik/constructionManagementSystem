import React, { useState , useEffect} from 'react'
import { Link } from "react-router-dom";


let taskColumnBooleans = {
    taskId: false,
    name: false,
    status: false,
    project: false,
    startDate: false,
    deadline: false,
    description: false,
};

export default function BriefTasksDisplay({ originalTaskList, projectIsNotANumber }) {
    const [taskList, setTaskList] = useState([]);

    useEffect(() => {
        setTaskList([...originalTaskList]);
    },[originalTaskList])


    const handleTaskColumnHeaderClick = (target) => {
        const sort_by = (field, reverse, primer) => {
            const key = primer
                ? function (x) {
                    return primer(x[field]);
                }
                : function (x) {
                    return x[field];
                };
            reverse = !reverse ? 1 : -1;

            return function (a, b) {
                return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
            };
        };

        switch (target) {
            case "TaskId":
                taskColumnBooleans.taskId
                    ? (taskColumnBooleans.taskId = false)
                    : (taskColumnBooleans.taskId = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("id", taskColumnBooleans.taskId, parseInt)
                    )
                );

                break;
            case "Name":
                taskColumnBooleans.name
                    ? (taskColumnBooleans.name = false)
                    : (taskColumnBooleans.name = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("name", taskColumnBooleans.name, (a) => a.toUpperCase())
                    )
                );
                break;
            case "Status":
                taskColumnBooleans.status
                    ? (taskColumnBooleans.status = false)
                    : (taskColumnBooleans.status = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("status", taskColumnBooleans.status, (a) => a.toUpperCase())
                    )
                );
                break;
            case "Project":
                taskColumnBooleans.project
                    ? (taskColumnBooleans.project = false)
                    : (taskColumnBooleans.project = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by(projectIsNotANumber?"project": "projectId", taskColumnBooleans.project, projectIsNotANumber? (a) => a.name.toUpperCase(): parseInt)
                    )
                );
                break;
            case "StartDate":
                taskColumnBooleans.startDate
                    ? (taskColumnBooleans.startDate = false)
                    : (taskColumnBooleans.startDate = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("startDate", taskColumnBooleans.startDate, (a) =>
                            a.toUpperCase()
                        )
                    )
                );
                break;
            case "Deadline":
                taskColumnBooleans.deadline
                    ? (taskColumnBooleans.deadline = false)
                    : (taskColumnBooleans.deadline = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("deadline", taskColumnBooleans.deadline, (a) =>
                            a.toUpperCase()
                        )
                    )
                );
                break;
            case "Description":
                taskColumnBooleans.description
                    ? (taskColumnBooleans.description = false)
                    : (taskColumnBooleans.description = true);

                setTaskList(
                    [...taskList].sort(
                        sort_by("description", taskColumnBooleans.description, (a) =>
                            a.toUpperCase()
                        )
                    )
                );
                break;
            default:
                break;
        }
    };

    return (
        <div>
            <h3>Task Table</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("TaskId")} >TaskId</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("Name")} >Name</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("Status")} >Status</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("Project")} >Project Containing</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("StartDate")} >StartDate</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("Deadline")} >Deadline</th>
                        <th scope="col" onClick={() => handleTaskColumnHeaderClick("Description")} >Description</th>
                    </tr>
                </thead>
                <tbody>
                    {taskList.map((task) => (
                        <tr className="table-active" key={task.id}>
                            <th scope="row">{task.id}</th>
                            <td>{task.name}</td>
                            <td>{task.status}</td>
                            <td>{task.project? task.project.name : task.projectId}</td>
                            <td>{task.startDate}</td>
                            <td>{task.deadline}</td>
                            <td>{task.description}</td>
                            <td>
                                <Link to={{ pathname: `/SingleTaskPage/${task.id}` }} >
                                    <button type="button" className="btn btn-warning">View</button>
                                </Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

