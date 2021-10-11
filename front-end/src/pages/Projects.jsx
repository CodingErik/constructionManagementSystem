import React, { useEffect, useState, useRef } from 'react';
import ProjectsTable from '../components/ProjectsTable';

import API from '../api/ProjectAPI';
import './Projects.css';

export default function Projects() {

    const [projects, setProjects] = useState([]);
    const [statusFilter, setStatusFilter] = useState("all");
    const [roomType, setRoomType] = useState();
    const [projectName, setProjectName] = useState();
    const [isPlumbing, setIsPlumbing] = useState();
    const [isElectric, setIsElectric] = useState();

    const projectNameRef = useRef();
    const roomTypeRef = useRef();

    useEffect(() => {
        API.getAllProjects(roomType ? roomType : null, projectName ? projectName : null)
            .then(res => {
                if(isElectric === false && isPlumbing === false){
                    let results = [];

                    res.data.forEach(project => {
                        if(project.plumbing === false && project.electric === false){
                            results.push(project);
                        }
                    })

                    setProjects(results);
                } else if(isElectric === false){
                    let results = [];

                    res.data.forEach(project => {
                        if(project.electric === false){
                            results.push(project);
                        }
                    }) 
                    
                    setProjects(results);
                } else if(isPlumbing === false){
                    let results = [];

                    res.data.forEach(project => {
                        if(project.plumbing === false){
                            results.push(project);
                        }
                    })  
                    
                    setProjects(results);
                } else {
                    setProjects(res.data);
                }

                
            })
            .catch(error => {
                console.error(error);
            })
    }, [roomType, projectName, isPlumbing, isElectric]);

    function setFilters(event) {
        event.preventDefault();
        projectNameRef.current.value !== null && setProjectName(projectNameRef.current.value);
        roomTypeRef.current.value !== null && setRoomType(roomTypeRef.current.value);
    }

    function resetFilters(event){
        event.preventDefault();
        roomTypeRef.current.value = null;
        projectNameRef.current.value = null;
        setRoomType(roomTypeRef.current.value);
        setProjectName(projectNameRef.current.value);
    }

    return (
        <div>
            <div className="statusFilter">
                <p style={{paddingTop:"13px"}}>Status : </p>
                <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <input onClick={() => setStatusFilter("all")}  type="radio" className="btn-check" name="btnradio" id="allStatusFilter" autoComplete="off" defaultChecked="" />
                    <label className="btn btn-outline-primary" htmlFor="allStatusFilter">All</label>
                    <input onClick={() => setStatusFilter("in_progress")} type="radio" className="btn-check" name="btnradio" id="inProgressStatusFilter" autoComplete="off" defaultChecked="" />
                    <label className="btn btn-outline-primary" htmlFor="inProgressStatusFilter">In Progress</label>
                    <input onClick={() => setStatusFilter("completed")} type="radio" className="btn-check" name="btnradio" id="completedStatusFilter" autoComplete="off" defaultChecked="" />
                    <label className="btn btn-outline-primary" htmlFor="completedStatusFilter">Completed</label>
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
                        <input type="radio" onClick={() => setIsPlumbing(true)} className="btn-check" name="btnradio" id="btnradioPlumbingYes" autoComplete="off" defaultChecked="" />
                        <label className="btn btn-outline-secondary" htmlFor="btnradioPlumbingYes">Yes</label>
                        <input type="radio" onClick={() => setIsPlumbing(false)} className="btn-check" name="btnradio" id="btnradioPlumbingNo" autoComplete="off" defaultChecked="" />
                        <label className="btn btn-outline-secondary" htmlFor="btnradioPlumbingNo">No</label>
                    </div>
                </div>
                <div className="electricityFilter filter">
                    <p>Electricity</p>
                    <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio" onClick={() => setIsElectric(true)} className="btn-check" name="btnradio" id="btnradioElectricityYes" autoComplete="off" defaultChecked="" />
                        <label className="btn btn-outline-secondary" htmlFor="btnradioElectricityYes">Yes</label>
                        <input type="radio" onClick={() => setIsElectric(false)} className="btn-check" name="btnradio" id="btnradioElectricityNo" autoComplete="off" defaultChecked="" />
                        <label className="btn btn-outline-secondary" htmlFor="btnradioElectricityNo">No</label>
                    </div>
                </div>
                <button className="filterButton" onClick={(event) => setFilters(event)}>Filter</button>
                <button className="filterButton" onClick={(event) => resetFilters(event)}>Reset</button>
            </div>

            <ProjectsTable 
                projects={projects} 
                statusFilter={statusFilter}
            />
        </div>
    )
}
