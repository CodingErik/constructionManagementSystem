import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import '../pages/Tasks.css';

export default function TasksTable(props) {
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState("all");

  useEffect(() => {
    setTasks(props.tasks);
    setStatusFilter(props.statusFilter);
  }, [props]);

  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Deadline</th>
          <th scope="col">Start</th>
          <th scope="col">Project id</th>
          <th scope="col">Project Name</th>
          <th scope="col">Employee id</th>
          <th scope="col">Employee Name</th>
          <th scope="col">Status</th>
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
                  <td className={`${task.status} taskStatus`}>{task.status}</td>
                  <td>
                    <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                      <button type="button" className="btn btn-warning">
                        View
                      </button>
                    </Link>
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
                  <td className={`${task.status} taskStatus`}>{task.status}</td>
                  <td>
                    <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                      <button type="button" className="btn btn-warning">
                        View
                      </button>
                    </Link>
                  </td>
                </tr>
              )
            );
          })}
      </tbody>
    </table>
  );
}
