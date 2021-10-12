import React, { useEffect, useState } from 'react';

export default function ProjectsTable(props){

    const [projects, setProjects] = useState([]);
    const [statusFilter, setStatusFilter] = useState("all");

    useEffect(() => {
        setProjects(props.projects);
        setStatusFilter(props.statusFilter);
    },[props])


    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Deadline</th>
                    <th scope="col">Start</th>
                    <th scope="col">Room</th>
                    <th scope="col">Plumbing</th>
                    <th scope="col">Electric</th>
                    <th scope="col">Material</th>
                    <th scope="col">Labor</th>
                    <th scope="col">Total</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>
            <tbody>
                {statusFilter === "all" && projects.map(project => {
                    return (
                        <tr key={project.id}>
                            <th scope="row">{project.id}</th>
                            <td>{project.name}</td>
                            <td>{project.deadline}</td>
                            <td>{project.startDate}</td>
                            <td>{project.roomType}</td>
                            <td>{project.plumbing.toString()}</td>
                            <td>{project.electric.toString()}</td>
                            <td>${project.materialBudget.toLocaleString()}</td>
                            <td>${project.laborBudget.toLocaleString()}</td>
                            <td>${project.totalBudget.toLocaleString()}</td>
                            <td className={`${project.status} projectStatus`}>{project.status}</td>
                        </tr>
                    )
                })}

                {statusFilter === "in_progress" && projects.map(project => {
                    return (
                        project.status === "in_progress" &&
                        <tr key={project.id}>
                            <th scope="row">{project.id}</th>
                            <td>{project.name}</td>
                            <td>{project.deadline}</td>
                            <td>{project.startDate}</td>
                            <td>{project.roomType}</td>
                            <td>{project.plumbing.toString()}</td>
                            <td>{project.electric.toString()}</td>
                            <td>${project.materialBudget.toLocaleString()}</td>
                            <td>${project.laborBudget.toLocaleString()}</td>
                            <td>${project.totalBudget.toLocaleString()}</td>
                            <td className={`${project.status} projectStatus`}>{project.status}</td>
                        </tr>
                    )
                })}

                {statusFilter === "completed" && projects.map(project => {
                    return (
                        project.status === "completed" &&
                        <tr key={project.id}>
                            <th scope="row">{project.id}</th>
                            <td>{project.name}</td>
                            <td>{project.deadline}</td>
                            <td>{project.startDate}</td>
                            <td>{project.roomType}</td>
                            <td>{project.plumbing.toString()}</td>
                            <td>{project.electric.toString()}</td>
                            <td>${project.materialBudget.toLocaleString()}</td>
                            <td>${project.laborBudget.toLocaleString()}</td>
                            <td>${project.totalBudget.toLocaleString()}</td>
                            <td className={`${project.status} projectStatus`}>{project.status}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )

}