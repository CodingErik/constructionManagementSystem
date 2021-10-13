import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

let columnBooleans = {
    projectId: false,
    name: false,
    status: false,
    startDate: false,
    deadline: false,
};

function BriefProjectsDisplay({ originalProjectLists }) {
    const [projectList, setProjectList] = useState([])

    useEffect(() => {
        setProjectList([...originalProjectLists]);
    },[originalProjectLists])

    const handleProjectColumnHeaderClick = (target) => {
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

        if (target === "Name") {
            if (columnBooleans.name) {
                columnBooleans.name = false;
            } else {
                columnBooleans.name = true;
            }
            setProjectList(
                [...projectList].sort(
                    sort_by("name", columnBooleans.name, (a) => a.toUpperCase())
                )
            );
        } else if (target === "ProjectId") {
            if (columnBooleans.projectId) {
                columnBooleans.projectId = false;
            } else {
                columnBooleans.projectId = true;
            }
            setProjectList(
                [...projectList].sort(sort_by("id", columnBooleans.projectId, parseInt))
            );
        } else if (target === "Status") {
            if (columnBooleans.status) {
                columnBooleans.status = false;
            } else {
                columnBooleans.status = true;
            }
            setProjectList(
                [...projectList].sort(
                    sort_by("status", columnBooleans.status, (a) => a.toUpperCase())
                )
            );
        } else if (target === "StartDate") {
            if (columnBooleans.startDate) {
                columnBooleans.startDate = false;
            } else {
                columnBooleans.startDate = true;
            }
            setProjectList(
                [...projectList].sort(
                    sort_by("startDate", columnBooleans.startDate, (a) => a.toUpperCase())
                )
            );
        } else if (target === "Deadline") {
            if (columnBooleans.deadline) {
                columnBooleans.deadline = false;
            } else {
                columnBooleans.deadline = true;
            }
            setProjectList(
                [...projectList].sort(
                    sort_by("deadline", columnBooleans.deadline, (a) => a.toUpperCase())
                )
            );
        }
    };

    return (
        <div>
            <h3>Projects Table</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col" onClick={() => handleProjectColumnHeaderClick("ProjectId")}>Project Id</th>
                        <th scope="col" onClick={() => handleProjectColumnHeaderClick("Name")}>Name</th>
                        <th scope="col" onClick={() => handleProjectColumnHeaderClick("Status")}>Status</th>
                        <th scope="col" onClick={() => handleProjectColumnHeaderClick("StartDate")}>Start Date</th>
                        <th scope="col" onClick={() => handleProjectColumnHeaderClick("Deadline")}>Deadline</th>
                    </tr>
                </thead>
                <tbody>
                    {projectList.map((project) => (
                        <tr className="table-active" key={project.id}>
                            <th scope="row">{project.id}</th>
                            <td>{project.name}</td>
                            <td>{project.status}</td>
                            <td>{project.startDate}</td>
                            <td>{project.deadline}</td>
                            <td>
                                <Link to={{ pathname: `/SingleProjectPage/${project.id}` }} >
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

export default BriefProjectsDisplay;