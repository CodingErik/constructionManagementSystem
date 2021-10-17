import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../pages/Tasks.css";
import { BsFillTrashFill } from "react-icons/bs";
import { TaskAPI } from "../api";

const columnBooleans = {
  id: false,
  name: false,
  deadline: false,
  startDate: false,
  projectId: false,
  projectName: false,
  employeeId: false,
  employeeName: false,
  status: false
}

export default function TasksTable(props) {
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState("all");
  useEffect(() => {
    setTasks(props.tasks);
    setStatusFilter(props.statusFilter);
  }, [props]);

  const handleDeleteTaskById = (taskId) => {
    TaskAPI.deleteTask(taskId);
    setTasks([...tasks].filter((task) => task.id !== taskId));
  };

  const handleTasksColumnHeaderClick = (
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
    setTasks(
      [...tasks].sort(
        sort_by(
          neededVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
  };

  return (
    <div className="table-responsive mb-5">
      <table
        className="table table-hover table-striped m-auto"
        style={{
          verticalAlign: "baseline",
          width: "100%",
        }}
      >
        <thead>
          <tr>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("id", "id", parseInt)}>Id</th>
            <th className="col-2" onClick={() => handleTasksColumnHeaderClick("name", "name", (a) =>a.toUpperCase())}>Name</th>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("deadline", "deadline", (a) =>a.toUpperCase())}>Deadline</th>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("startDate", "startDate", (a) =>a.toUpperCase())}>Start</th>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("project.id", "projectId", parseInt)}>Project id</th>
            <th className="col-2" onClick={() => handleTasksColumnHeaderClick("project.name", "projectName", (a) =>a.toUpperCase())}>Project Name</th>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("employee.id", "employeeId", parseInt)}>Employee id</th>
            <th className="col-2" onClick={() => handleTasksColumnHeaderClick("employee.name", "employeeName", (a) =>a.toUpperCase())}>Employee Name</th>
            <th className="col-1" onClick={() => handleTasksColumnHeaderClick("status", "status", (a) =>a.toUpperCase())}>Status</th>
          </tr>
        </thead>
        <tbody>
          {statusFilter === "all" &&
            tasks.map((task) => {
              return (
                <tr key={task.id}>
                  <th scope="row">{task.id}</th>
                  <td>{task.name}</td>
                  <td>{task.deadline}</td>
                  <td>{task.startDate}</td>
                  <td>{task.project.id} </td>
                  <td>{task.project.name} </td>
                  <td>{task.employee.id}</td>
                  <td>{task.employee.name}</td>
                  <td className={`${task.status} taskStatus`}>{task.status}</td>
                  <td>
                    <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                      <button type="button" className="btn btn-warning">
                        View
                      </button>
                    </Link>
                  </td>
                  <td>
                    {props.authority === "admin" && (
                      <button
                        type="button"
                        className="btn btn-danger"
                        onClick={() => handleDeleteTaskById(task.id)}
                        disabled={props.authority !== "admin"}
                      >
                        <BsFillTrashFill></BsFillTrashFill>
                      </button>
                    )}
                  </td>
                </tr>
              );
            })}

          {statusFilter === "in_progress" &&
            tasks.map((task) => {
              return (
                task.status === "in_progress" && (
                  <tr key={task.id}>
                    <th scope="row">{task.id}</th>
                    <td>{task.name}</td>
                    <td>{task.deadline}</td>
                    <td>{task.startDate}</td>
                    <td>{task.project.id} </td>
                    <td>{task.project.name} </td>
                    <td>{task.employee.id}</td>
                    <td>{task.employee.name}</td>
                    <td className={`${task.status} taskStatus`}>
                      {task.status}
                    </td>
                    <td>
                      <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                        <button type="button" className="btn btn-warning">
                          View
                        </button>
                      </Link>
                    </td>
                    <td>
                      {props.authority === "admin" && (
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => handleDeleteTaskById(task.id)}
                          disabled={props.authority !== "admin"}
                        >
                          <BsFillTrashFill></BsFillTrashFill>
                        </button>
                      )}
                    </td>
                  </tr>
                )
              );
            })}

          {statusFilter === "completed" &&
            tasks.map((task) => {
              return (
                task.status === "completed" && (
                  <tr key={task.id}>
                    <th scope="row">{task.id}</th>
                    <td>{task.name}</td>
                    <td>{task.deadline}</td>
                    <td>{task.startDate}</td>
                    <td>{task.project.id} </td>
                    <td>{task.project.name} </td>
                    <td>{task.employee.id}</td>
                    <td>{task.employee.name}</td>
                    <td className={`${task.status} taskStatus`}>
                      {task.status}
                    </td>
                    <td>
                      <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                        <button type="button" className="btn btn-warning">
                          View
                        </button>
                      </Link>
                    </td>
                    <td>
                      {props.authority === "admin" && (
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => handleDeleteTaskById(task.id)}
                          disabled={props.authority !== "admin"}
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
