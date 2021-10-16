import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { ProjectAPI, EmployeeAPI } from "../api/index";
import EmployeeListTableForProject from "../components/singleProject/EmployeeListTableForProject";
import ProjectForm from "../components/singleProject/ProjectForm";
import TaskListTableForProject from "../components/singleProject/TaskListTableForProject";
import decode from "jwt-decode";
import redirectIfTokenNull from "../components/RedirectHelper";
import ResourcesForSingleProjectPage from "../components/resources/ResourcesForSingleProjectPage";

function SingleProjectPage() {
  redirectIfTokenNull();
  const { projectId } = useParams();
  const [project, setProject] = useState({});
  const [user, setUser] = useState({});
  const [hasAuthority, setHasAuthority] = useState(false);
  const token = localStorage.getItem("token")
    ? decode(JSON.parse(localStorage.getItem("token")))
    : "illegal";

  useEffect(() => {
    async function fetchData() {
      const projectInfo = await ProjectAPI.getProjectById(projectId);
      const userInfo = await EmployeeAPI.getEmployeeByUsername(token.sub);
      setUser(userInfo.data);
      setProject(projectInfo.data);
    }
    fetchData();
  }, []);

  useEffect(() => {
    if (
      (user.project?.id === project.id && user.title === "architect") ||
      user.title === "admin"
    ) {
      setHasAuthority(true);
    } else {
      setHasAuthority(false);
    }
  }, [user, project]);

  return (
    <div className="container">
      <div className="row">
        <ProjectForm hasAuthority={hasAuthority} project={project} />
      </div>

      <div className="row">
        <EmployeeListTableForProject
          projectId={projectId}
          hasAuthority={hasAuthority}
        ></EmployeeListTableForProject>
      </div>

      <div className="row">
        <TaskListTableForProject
          hasAuthority={hasAuthority}
          projectId={projectId}
          projectName={project.name}
        ></TaskListTableForProject>
      </div>
      <div>
        <ResourcesForSingleProjectPage></ResourcesForSingleProjectPage>
      </div>
    </div>
  );
}

export default SingleProjectPage;
