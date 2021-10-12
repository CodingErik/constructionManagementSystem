import React, { useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import { ProjectAPI, EmployeeAPI } from "../api/index";
import EmployeeListTableForProject from "../components/singleProject/EmployeeListTableForProject";
import ProjectForm from "../components/singleProject/ProjectForm";

function SingleProjectPage({ }) {
  const { projectId } = useParams();
  const [project, setProject] = useState({});
  const [hasAuthority, setHasAuthority] = useState(true);

  useEffect(() => {
    ProjectAPI.getProjectById(projectId).then((response) => {
      setProject(response.data);
    });
  }, []);


  



  return (
    <div>
      <ProjectForm hasAuthority={hasAuthority} project={project}/>
      <EmployeeListTableForProject
        projectId={projectId}
      ></EmployeeListTableForProject>
    </div>
  );
}

export default SingleProjectPage;
