import React, { useEffect, useState, useContext } from "react";
import API from "../api/EmployeeAPI";
import BriefProjectsDisplay from "../components/home/BriefProjectsDisplay";
import BriefTasksDisplay from "../components/home/BriefTasksDisplay";
import decode from "jwt-decode";
import redirectIfTokenNull from "../components/RedirectHelper";
import DisplayBasicInformation from "../components/myProfile/DisplayBasicInformation";

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
        setUserProject([{ ...data.project }]);
        setUserTask([...data.taskList]);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [username]);

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
          <DisplayBasicInformation userInfo={userInfo}/>
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
                originalTaskList={userTasks}
                projectIsNotANumber={false}
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
