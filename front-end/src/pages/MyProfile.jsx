import React, { useEffect, useState, useContext } from "react";
import API from "../api/EmployeeAPI";
import BriefProjectsDisplay from "../components/home/BriefProjectsDisplay";
import BriefTasksDisplay from "../components/home/BriefTasksDisplay";
import decode from "jwt-decode";
import redirectIfTokenNull from "../components/RedirectHelper";

let taskColumnBooleans = {
  TaskId: false,
  Task: false,
  Status: false,
  StartDate: false,
  Deadline: false,
  description: false,
};

export default function MyProfile() {
  redirectIfTokenNull();

  const [userInfo, setUserInfo] = useState({});
  const [userProjects, setUserProject] = useState([]);
  const [userTasks, setUserTask] = useState([]);
  const username = localStorage.getItem("token")
    ? decode(JSON.parse(localStorage.getItem("token"))).sub
    : null;

  useEffect(() => {
    API.getEmployeeByUsername(username)
      .then(({ data }) => {
        setUserInfo(data);
        setUserProject(() => [data.project]);
        setUserTask(() => [data.taskList]);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [username]);

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
    console.log(target);
    switch (target) {
      case "TaskId":
        taskColumnBooleans.TaskId
          ? (taskColumnBooleans.TaskId = false)
          : (taskColumnBooleans.TaskId = true);

        setUserTask(
          [...userTasks].sort(
            sort_by("id", taskColumnBooleans.TaskId, parseInt)
          )
        );

        break;
      case "Task":
        taskColumnBooleans.Task
          ? (taskColumnBooleans.Task = false)
          : (taskColumnBooleans.Task = true);

        setUserTask(
          [...userTasks].sort(
            sort_by("Task", taskColumnBooleans.Task, (a) => a.toUpperCase())
          )
        );
        break;
      case "Status":
        taskColumnBooleans.Status
          ? (taskColumnBooleans.Status = false)
          : (taskColumnBooleans.Status = true);

        setUserTask(
          [...userTasks].sort(
            sort_by("Status", taskColumnBooleans.Status, (a) => a.toUpperCase())
          )
        );
        break;
      case "StartDate":
        taskColumnBooleans.StartDate
          ? (taskColumnBooleans.StartDate = false)
          : (taskColumnBooleans.StartDate = true);

        setUserTask(
          [...userTasks].sort(
            sort_by("StartDate", taskColumnBooleans.StartDate, (a) =>
              a.toUpperCase()
            )
          )
        );
        break;
      case "Deadline":
        taskColumnBooleans.Deadline
          ? (taskColumnBooleans.Deadline = false)
          : (taskColumnBooleans.Deadline = true);

        setUserTask(
          [...userTasks].sort(
            sort_by("Deadline", taskColumnBooleans.Deadline, (a) =>
              a.toUpperCase()
            )
          )
        );
        break;
      case "description":
        taskColumnBooleans.description
          ? (taskColumnBooleans.description = false)
          : (taskColumnBooleans.description = true);

        setUserTask(
          [...userTasks].sort(
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
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col col-4-lg">
            <img
              src="https://via.placeholder.com/350"
              //   add image for the user aws implementation
              className="rounded-circle"
              alt="profile image"
            ></img>
          </div>
          <div className="col col-8-lg">
            <div className="row mb-3">
              <div className="col-4">
                <h2>My Info </h2>
              </div>
              <div className="col-5"></div>
              <div className="col-3">
                <button
                  style={{ display: "inline" }}
                  type="button"
                  className="btn btn-warning"
                >
                  Edit Info
                </button>
              </div>
            </div>
            <div className="row">
              <div className="col-12">
                <ul className="list-group text-black">
                  <div className="list-group-item bg-transparent text-start ">
                    <span className="employee-email">Id: {userInfo.id}</span>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <span className="employee-email">
                      Title: {userInfo.title}
                    </span>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <span className="employee-email">
                      Name: {userInfo.name}
                    </span>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <span className="employee-email">Dob: {userInfo.dob}</span>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-name">Salary: {userInfo.salary}</p>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-role">
                      Experience: {userInfo.yearsOfExperience}
                    </p>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-department">Email: {userInfo.email}</p>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-department">
                      Phone: {userInfo.phoneNumber}
                    </p>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-department">
                      Username: {userInfo.username}
                    </p>
                  </div>
                  <div className="list-group-item bg-transparent text-start ">
                    <p id="employee-department">
                      User Since: {userInfo.userSince}
                    </p>
                  </div>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div className="row mt-5">
          <div className="col ">
            {userProjects.length ? (
              <BriefProjectsDisplay
                originalProjectLists={userProjects}
              ></BriefProjectsDisplay>
            ) : (
              <h2>No Available projects... </h2>
            )}
          </div>
          <div className=" col ">
            {userTasks.length ? (
              <BriefTasksDisplay
                taskList={userTasks}
                handleTaskColumnHeaderClick={handleTaskColumnHeaderClick}
              ></BriefTasksDisplay>
            ) : (
              <h2>No Available tasks... </h2>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
