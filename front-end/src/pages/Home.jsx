import React, { useEffect, useState } from "react";
import OnGoingProjectsDisplay from "../components/home/OnGoingProjectsDisplay";
import ProjectPieChart from "../components/home/ProjectPieChart";
import EmployeeListTable from "../components/home/EmployeeListTable";
import { ProjectAPI, EmployeeAPI } from "../api/index";

let columnBooleans = {
  projectId: false,
  name: false,
  status: false,
  startDate: false,
  deadline: false,
};


function Home() {

  const [projectList, setProjectList] = useState([]);
  const [statusCount, setStatusCount] = useState({
    inProgress: 0,
    cancelled: 0,
    completed: 0,
  });
  const [employeeList, setEmployeeList] = useState([]);

  // Filter column descending and ascending
  const handleProjectColumnHeaderClick = (target) => {
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

    if (target === "Name") {
      if (columnBooleans.name) {
        columnBooleans.name = false;
      } else {
        columnBooleans.name = true;
      }
      setProjectList(
        [...projectList].sort(
          sort_by("name", columnBooleans.name, (a) => a.toUpperCase())
        )
      );
    } else if (target === "ProjectId") {
      if (columnBooleans.projectId) {
        columnBooleans.projectId = false;
      } else {
        columnBooleans.projectId = true;
      }
      setProjectList(
        [...projectList].sort(sort_by("id", columnBooleans.projectId, parseInt))
      );
    } else if (target === "Status") {
      if (columnBooleans.status) {
        columnBooleans.status = false;
      } else {
        columnBooleans.status = true;
      }
      setProjectList(
        [...projectList].sort(
          sort_by("status", columnBooleans.status, (a) => a.toUpperCase())
        )
      );
    } else if (target === "StartDate") {
      if (columnBooleans.startDate) {
        columnBooleans.startDate = false;
      } else {
        columnBooleans.startDate = true;
      }
      setProjectList(
        [...projectList].sort(
          sort_by("startDate", columnBooleans.startDate, (a) => a.toUpperCase())
        )
      );
    } else if (target === "Deadline") {
      if (columnBooleans.deadline) {
        columnBooleans.deadline = false;
      } else {
        columnBooleans.deadline = true;
      }
      setProjectList(
        [...projectList].sort(
          sort_by("deadline", columnBooleans.deadline, (a) => a.toUpperCase())
        )
      );
    }
  };

  useEffect(() => {
    ProjectAPI.getAllProjects().then((response) => {
      setProjectList([...response.data]);
      response.data.forEach((project) => {
        if (project.status === "in_progress") {
          setStatusCount((prevState) => ({
            ...prevState,
            inProgress: prevState.inProgress++,
          }));
        } else if (project.status === "completed") {
          setStatusCount((prevState) => ({
            ...prevState,
            completed: prevState.completed++,
          }));
        } else if (project.status === "cancelled") {
          setStatusCount((prevState) => ({
            ...prevState,
            cancelled: prevState.cancelled++,
          }));
        }
      });
    });
    EmployeeAPI.getAllEmployees().then((response) => {
      setEmployeeList(response.data);
      console.log(response.data);
    });
  }, []);

  return (
    <div className="container">
      <div className="row">
        <div className="col col-lg-7">
          <OnGoingProjectsDisplay
            projectList={projectList}
            handleProjectColumnHeaderClick={handleProjectColumnHeaderClick}
          ></OnGoingProjectsDisplay>
        </div>
        <div className="col col-lg-5">
          <div className="row">
            <ProjectPieChart statusCount={statusCount}></ProjectPieChart>
          </div>
          <div className="row">
            <EmployeeListTable employeeList={employeeList} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
