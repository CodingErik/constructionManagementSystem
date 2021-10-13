import React, { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import { ProjectAPI, EmployeeAPI } from "../api/index";
import EmployeeListTableForProject from "../components/singleProject/EmployeeListTableForProject";
import ProjectForm from "../components/singleProject/ProjectForm";
import decode from "jwt-decode";
import redirectIfTokenNull from "../components/RedirectHelper";

function SingleProjectPage({}) {
  redirectIfTokenNull();
  const { projectId } = useParams();
  const [project, setProject] = useState({});
  const [hasAuthority, setHasAuthority] = useState(false);
  const token = decode(JSON.parse(localStorage.getItem("token")));
  useEffect(() => {
    ProjectAPI.getProjectById(projectId).then((response) => {
      setProject(response.data);
      setHasAuthority(token.authorities === "Architect" ? true: false)
    });
  }, []);

  return (
    <div>
      <ProjectForm hasAuthority={hasAuthority} project={project} />
      <EmployeeListTableForProject
        projectId={projectId} hasAuthority={hasAuthority}
      ></EmployeeListTableForProject>
    </div>
  );
}

export default SingleProjectPage;
