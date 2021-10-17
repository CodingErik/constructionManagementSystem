import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import {
  ProjectAPI,
  EmployeeAPI,
  MaterialAPI,
  MachineryAPI,
} from "../api/index";
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
        <div className="col-xl-6 col-lg-6 col-md-12 col-sm-12">
          <EmployeeListTableForProject
            projectId={projectId}
            hasAuthority={hasAuthority}
          ></EmployeeListTableForProject>
        </div>
        <div className="col-xl-6 col-lg-6 col-md-12 col-sm-12">
          <TaskListTableForProject
            hasAuthority={hasAuthority}
            projectId={projectId}
            projectName={project.name}
          ></TaskListTableForProject>
        </div>
      </div>

      <div>
        <ResourcesForSingleProjectPage
          projectId={projectId}
          hasAuthority={hasAuthority}
        ></ResourcesForSingleProjectPage>
      </div>
    </div>
  );
}

export default SingleProjectPage;
