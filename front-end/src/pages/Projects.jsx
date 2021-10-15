import React, { useEffect, useState, useRef } from "react";
import ProjectsTable from "../components/ProjectsTable";
import redirectIfTokenNull from "../components/RedirectHelper";
import API from "../api/ProjectAPI";
import { ProjectAPI } from "../api";
import "../assets/Projects.css";
import AddProjectModal from "../components/project/AddProjectModal";
import decode from "jwt-decode";
const authority = decode(JSON.parse(localStorage.getItem("token"))).authorities;

export default function Projects() {
  redirectIfTokenNull();
  const [projects, setProjects] = useState([]);
  const [statusFilter, setStatusFilter] = useState("all");
  const [roomType, setRoomType] = useState("");
  const [name, setName] = useState("");
  const [isPlumbing, setIsPlumbing] = useState();
  const [isElectric, setIsElectric] = useState();
  const projectNameRef = useRef();
  const roomTypeRef = useRef();

  const handleNewProjectSubmit = (newProjectInfo) => {
    ProjectAPI.addProject(newProjectInfo);
    ProjectAPI.getAllProjects().then((res) => {
      setProjects([...res.data]);
    });
  };

  useEffect(() => {
    API.getAllProjects(
      roomType.length > 0 ? roomType : null,
      name.length > 0 ? name : null
    )
      .then((res) => {
        if (isElectric === false && isPlumbing === false) {
          let results = [];

          res.data.forEach((project) => {
            if (project.plumbing === false && project.electric === false) {
              results.push(project);
            }
          });

          setProjects(results);
        } else if (isElectric === false) {
          let results = [];

          res.data.forEach((project) => {
            if (project.electric === false) {
              results.push(project);
            }
          });

          setProjects(results);
        } else if (isPlumbing === false) {
          let results = [];

          res.data.forEach((project) => {
            if (project.plumbing === false) {
              results.push(project);
            }
          });

          setProjects(results);
        } else {
          setProjects(res.data);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }, [roomType, name, isPlumbing, isElectric]);

  function setFilters(event) {
    event.preventDefault();
    projectNameRef.current.value !== null &&
      setName(projectNameRef.current.value);
    roomTypeRef.current.value !== null &&
      setRoomType(roomTypeRef.current.value);
  }

  function resetFilters(event) {
    event.preventDefault();

    roomTypeRef.current.value = "";
    setRoomType(roomTypeRef.current.value);

    projectNameRef.current.value = "";
    setName(projectNameRef.current.value);
  }

  return (
    <div className="container-fluid mt-3">
      {(authority === "admin" || authority === "architect") && (
        <div>
          <button
            type="button"
            className="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#addProject"
          >
            Add Project
          </button>
          <AddProjectModal
            handleNewProjectSubmit={handleNewProjectSubmit}
            hasAuthority={
              authority === "admin" || authority === "architect" ? true : false
            }
            modalId="addProject"
          ></AddProjectModal>
        </div>
      )}
      <div className="row">
        <div className="statusFilter">
          <div
            className="btn-group"
            role="group"
            aria-label="Basic radio toggle button group"
          >
            <p
              style={{ paddingTop: "13px", marginRight: "20px" }}
              className="textField"
            >
              Status :{" "}
            </p>
            <input
              onClick={() => setStatusFilter("all")}
              type="radio"
              className="btn-check"
              name="btnradio"
              id="allStatusFilter"
              autoComplete="off"
              defaultChecked=""
            />
            <label
              className="btn btn-outline-primary btn-sm"
              htmlFor="allStatusFilter"
            >
              All
            </label>
            <input
              onClick={() => setStatusFilter("in_progress")}
              type="radio"
              className="btn-check"
              name="btnradio"
              id="inProgressStatusFilter"
              autoComplete="off"
              defaultChecked=""
            />
            <label
              className="btn btn-outline-primary btn-sm"
              htmlFor="inProgressStatusFilter"
            >
              In Progress
            </label>
            <input
              onClick={() => setStatusFilter("completed")}
              type="radio"
              className="btn-check"
              name="btnradio"
              id="completedStatusFilter"
              autoComplete="off"
              defaultChecked=""
            />
            <label
              className="btn btn-outline-primary btn-sm"
              htmlFor="completedStatusFilter"
            >
              Completed
            </label>
          </div>
          <div className="plumbingFilter filter">
            <p className="textField">Plumbing</p>
            <div
              className="btn-group"
              role="group"
              aria-label="Basic radio toggle button group"
            >
              <input
                type="radio"
                onClick={() => setIsPlumbing(true)}
                className="btn-check"
                name="btnradio"
                id="btnradioPlumbingYes"
                autoComplete="off"
                defaultChecked=""
              />
              <label
                className="btn btn-outline-secondary btn-sm"
                htmlFor="btnradioPlumbingYes"
              >
                Yes
              </label>
              <input
                type="radio"
                onClick={() => setIsPlumbing(false)}
                className="btn-check"
                name="btnradio"
                id="btnradioPlumbingNo"
                autoComplete="off"
                defaultChecked=""
              />
              <label
                className="btn btn-outline-secondary btn-sm"
                htmlFor="btnradioPlumbingNo"
              >
                No
              </label>
            </div>
          </div>
          <div className="electricityFilter filter">
            <p className="textField">Electricity</p>
            <div
              className="btn-group"
              role="group"
              aria-label="Basic radio toggle button group"
            >
              <input
                type="radio"
                onClick={() => setIsElectric(true)}
                className="btn-check"
                name="btnradio"
                id="btnradioElectricityYes"
                autoComplete="off"
                defaultChecked=""
              />
              <label
                className="btn btn-outline-secondary btn-sm"
                htmlFor="btnradioElectricityYes"
              >
                Yes
              </label>
              <input
                type="radio"
                onClick={() => setIsElectric(false)}
                className="btn-check"
                name="btnradio"
                id="btnradioElectricityNo"
                autoComplete="off"
                defaultChecked=""
              />
              <label
                className="btn btn-outline-secondary btn-sm"
                htmlFor="btnradioElectricityNo"
              >
                No
              </label>
            </div>
          </div>
        </div>
      </div>

      <div className="col">
        <div className="otherFilters">
          <div className="nameFilter filter">
            <p className="textField">Project Name</p>
            <input ref={projectNameRef} className="textInput" />
          </div>
          <div className="roomFilter filter">
            <p className="textField">Room Type</p>
            <input ref={roomTypeRef} className="textInput" />
          </div>
          <button
            className="filterButton"
            onClick={(event) => setFilters(event)}
          >
            Filter
          </button>
          <button
            className="filterButton"
            onClick={(event) => resetFilters(event)}
          >
            Reset
          </button>
        </div>
      </div>

      <ProjectsTable projects={projects} statusFilter={statusFilter} />
    </div>
  );
}
