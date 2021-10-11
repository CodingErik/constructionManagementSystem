import React, { useEffect, useState, useRef } from 'react';
import ProjectsTable from '../components/ProjectsTable';

import API from '../api/ProjectAPI';
import './Projects.css';

export default function Projects() {

    const [projects, setProjects] = useState([]);
    const [statusFilter, setStatusFilter] = useState("all");

    const projectNameRef = useRef();
    const roomTypeRef = useRef();

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
                    <input onClick={() => setStatusFilter("all")}  type="radio" className="btn-check" name="btnradio" id="allStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="allStatusFilter">All</label>
                    <input onClick={() => setStatusFilter("in_progress")} type="radio" className="btn-check" name="btnradio" id="inProgressStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="inProgressStatusFilter">In Progress</label>
                    <input onClick={() => setStatusFilter("completed")} type="radio" className="btn-check" name="btnradio" id="completedStatusFilter" autocomplete="off" checked="" />
                    <label className="btn btn-outline-primary" for="completedStatusFilter">Completed</label>
                </div>
            </div>

            <div className="otherFilters">
                <div className="nameFilter filter">
                    <p>Project Name</p>
                    <input ref={projectNameRef} />
                </div>
                <div className="roomFilter filter">
                    <p>Room Type</p>
                    <input ref={roomTypeRef} />
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

            <ProjectsTable 
                projects={projects} 
                statusFilter={statusFilter}
                projectName={projectNameRef.current}
                roomType={roomTypeRef.current}
            />
        </div>
    )
}
