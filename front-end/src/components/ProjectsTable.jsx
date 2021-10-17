import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BsFillTrashFill } from "react-icons/bs";
import { ProjectAPI } from "../api";

const columnBooleans = {
  id: false,
  name: false,
  deadline: false,
  startDate: false,
  roomType: false,
  materialBudget: false,
  laborBudget: false,
  totalBudget: false,
  status: false,
};

export default function ProjectsTable({
  allProjects,
  prevStatusFilter,
  authority,
}) {
  const [projects, setProjects] = useState([]);
  const [statusFilter, setStatusFilter] = useState("all");

  useEffect(() => {
    setProjects(allProjects);
    setStatusFilter(prevStatusFilter);
  }, [allProjects, prevStatusFilter]);

  const handleDeleteProjectById = (projectId) => {
    ProjectAPI.deleteProjectById(projectId);
    console.log(projectId);
    setProjects([...projects].filter((project) => project.id !== projectId));
  };

  const handleProjectColumnHeaderClick = (
    neededVariable,
    booleanVariable,
    methodTranslate
  ) => {
    const sort_by = (neededField, reverse, primer) => {
      const getField = (obj, path) =>
        path.split(".").reduce((value, el) => value[el], obj);
      const key = primer
        ? function (x) {
            return primer(getField(x, neededField));
          }
        : function (x) {
            return getField(x, neededVariable);
          };
      reverse = !reverse ? 1 : -1;

      return function (a, b) {
        return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
      };
    };

    if (columnBooleans[booleanVariable]) {
      columnBooleans[booleanVariable] = false;
    } else {
      columnBooleans[booleanVariable] = true;
    }
    setProjects(
      [...projects].sort(
        sort_by(
          neededVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
  };

  return (
    <div className="table-responsive">
      <table className="table table-hover m-auto">
        <thead>
          <tr>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("id", "id", parseInt)
              }
            >
              Id
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("name", "name", (a) =>
                  a.toUpperCase()
                )
              }
            >
              Name
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("deadline", "deadline", (a) =>
                  a.toUpperCase()
                )
              }
            >
              Deadline
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("startDate", "startDate", (a) =>
                  a.toUpperCase()
                )
              }
            >
              Start
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("roomType", "roomType", (a) =>
                  a.toUpperCase()
                )
              }
            >
              Room
            </th>
            <th className="col-1">Plumbing</th>
            <th className="col-1">Electric</th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick(
                  "materialBudget",
                  "materialBudget",
                  parseInt
                )
              }
            >
              Material
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick(
                  "laborBudget",
                  "laborBudget",
                  parseInt
                )
              }
            >
              Labor
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick(
                  "totalBudget",
                  "totalBudget",
                  parseInt
                )
              }
            >
              Total
            </th>
            <th
              className="col-1"
              onClick={() =>
                handleProjectColumnHeaderClick("status", "status", (a) =>
                  a.toUpperCase()
                )
              }
            >
              Status
            </th>
          </tr>
        </thead>
        <tbody>
          {statusFilter === "all" &&
            projects.map((project) => {
              return (
                <tr key={project.id}>
                  <th scope="row">{project.id}</th>
                  <td>{project.name}</td>
                  <td>{project.deadline}</td>
                  <td>{project.startDate}</td>
                  <td>{project.roomType}</td>
                  <td>{project.plumbing ? "✔️" : "❌"}</td>
                  <td>{project.electric ? "✔️" : "❌"}</td>
                  <td>${project.materialBudget.toLocaleString()}</td>
                  <td>${project.laborBudget.toLocaleString()}</td>
                  <td>${project.totalBudget.toLocaleString()}</td>
                  <td className={`${project.status} projectStatus`}>
                    {project.status}
                  </td>
                  <td>
                    <Link to={{ pathname: `/SingleProjectPage/${project.id}` }}>
                      <button type="button" className="btn btn-warning">
                        View
                      </button>
                    </Link>
                  </td>
                  <td>
                    {authority === "admin" && (
                      <button
                        type="button"
                        className="btn btn-danger"
                        onClick={() => handleDeleteProjectById(project.id)}
                        disabled={authority !== "admin"}
                      >
                        <BsFillTrashFill></BsFillTrashFill>
                      </button>
                    )}
                  </td>
                </tr>
              );
            })}

          {statusFilter === "completed" &&
            projects.map((project) => {
              return (
                project.status === "completed" && (
                  <tr key={project.id}>
                    <th scope="row">{project.id}</th>
                    <td>{project.name}</td>
                    <td>{project.deadline}</td>
                    <td>{project.startDate}</td>
                    <td>{project.roomType}</td>
                    <td>{project.plumbing ? "✔️" : "❌"}</td>
                    <td>{project.electric ? "✔️" : "❌"}</td>
                    <td>${project.materialBudget.toLocaleString()}</td>
                    <td>${project.laborBudget.toLocaleString()}</td>
                    <td>${project.totalBudget.toLocaleString()}</td>
                    <td className={`${project.status} projectStatus`}>
                      {project.status}
                    </td>
                    <td>
                      <Link
                        to={{ pathname: `/SingleProjectPage/${project.id}` }}
                      >
                        <button type="button" className="btn btn-warning">
                          View
                        </button>
                      </Link>
                    </td>
                    <td>
                      {authority === "admin" && (
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => handleDeleteProjectById(project.id)}
                          disabled={authority !== "admin"}
                        >
                          <BsFillTrashFill></BsFillTrashFill>
                        </button>
                      )}
                    </td>
                  </tr>
                )
              );
            })}

          {statusFilter === "in_progress" &&
            projects.map((project) => {
              return (
                project.status === "in_progress" && (
                  <tr key={project.id}>
                    <th scope="row">{project.id}</th>
                    <td>{project.name}</td>
                    <td>{project.deadline}</td>
                    <td>{project.startDate}</td>
                    <td>{project.roomType}</td>
                    <td>{project.plumbing ? "✔️" : "❌"}</td>
                    <td>{project.electric ? "✔️" : "❌"}</td>
                    <td>${project.materialBudget.toLocaleString()}</td>
                    <td>${project.laborBudget.toLocaleString()}</td>
                    <td>${project.totalBudget.toLocaleString()}</td>
                    <td className={`${project.status} projectStatus`}>
                      {project.status}
                    </td>
                    <td>
                      <Link
                        to={{ pathname: `/SingleProjectPage/${project.id}` }}
                      >
                        <button type="button" className="btn btn-warning">
                          View
                        </button>
                      </Link>
                    </td>
                    <td>
                      {authority === "admin" && (
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => handleDeleteProjectById(project.id)}
                          disabled={authority !== "admin"}
                        >
                          <BsFillTrashFill></BsFillTrashFill>
                        </button>
                      )}
                    </td>
                  </tr>
                )
              );
            })}
        </tbody>
      </table>
    </div>
  );
}
