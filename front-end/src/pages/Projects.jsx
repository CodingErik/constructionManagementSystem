import React, { useEffect, useState } from 'react';
import API from '../api/ProjectAPI';
import './Projects.css';

export default function Projects() {

    const [projects, setProjects] = useState([]);

    useEffect(() => {
        API.getAllProjects()
            .then(res => {
                setProjects(res.data);
            })
            .catch(error => {
                console.error(error);
            })
    },[])

    return (
        <div>

            <div className="statusFilter">
                <p style={{paddingTop:"13px"}}>Status : </p>
                <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <input type="radio" className="btn-check" name="btnradio" id="allStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="allStatusFilter">All</label>
                    <input type="radio" className="btn-check" name="btnradio" id="inProgressStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="inProgressStatusFilter">In Progress</label>
                    <input type="radio" className="btn-check" name="btnradio" id="completedStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="completedStatusFilter">Completed</label>
                </div>
            </div>

            <div className="otherFilters">
                <div className="nameFilter filter">
                    <p>Project Name</p>
                    <input  />
                </div>
                <div className="roomFilter filter">
                    <p>Room Type</p>
                    <input  />
                </div>
                <div className="plumbingFilter filter">
                    <p>Plumbing</p>
                    <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio" className="btn-check" name="btnradio" id="btnradioPlumbingYes" autocomplete="off" checked="" />
                        <label className="btn btn-outline-secondary" for="btnradioPlumbingYes">Yes</label>
                        <input type="radio" className="btn-check" name="btnradio" id="btnradioPlumbingNo" autocomplete="off" checked="" />
                        <label className="btn btn-outline-secondary" for="btnradioPlumbingNo">No</label>
                    </div>
                </div>
                <div className="electricityFilter filter">
                    <p>Electricity</p>
                    <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio" className="btn-check" name="btnradio" id="btnradioElectricityYes" autocomplete="off" checked="" />
                        <label className="btn btn-outline-secondary" for="btnradioElectricityYes">Yes</label>
                        <input type="radio" className="btn-check" name="btnradio" id="btnradioElectricityNo" autocomplete="off" checked="" />
                        <label className="btn btn-outline-secondary" for="btnradioElectricityNo">No</label>
                    </div>
                </div>
                <button className="filterButton">Filter</button>
            </div>

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
                    {projects.map(project => {
                        return (
                            <tr key={project.id}>
                                <th scope="row">{project.id}</th>
                                <td>{project.name}</td>
                                <td>{project.deadline}</td>
                                <td>{project.startDate}</td>
                                <td>{project.roomType}</td>
                                <td>{project.isPlubming}</td>
                                <td>{project.isElectric}</td>
                                <td>${project.materialBudget.toLocaleString()}</td>
                                <td>${project.laborBudget.toLocaleString()}</td>
                                <td>${project.totalBudget.toLocaleString()}</td>
                                <td className={`${project.status} projectStatus`}>{project.status}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}
