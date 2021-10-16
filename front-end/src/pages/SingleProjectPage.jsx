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
import brickIcon from "../assets/brick.png";
import cementIcon from "../assets/cement.png";
import lumberIcon from "../assets/lumber.png";
import steelIcon from "../assets/steel.png";
import craneIcon from "../assets/crane.png";
import drillIcon from "../assets/drill.png";
import forkliftIcon from "../assets/forklift.png";
import ladderIcon from "../assets/ladder.png";

function SingleProjectPage() {
  redirectIfTokenNull();
  const { projectId } = useParams();
  const [project, setProject] = useState({});
  const [user, setUser] = useState({});
  const [materialAmount, setMaterialAmount] = useState({});
  const [materials, setMaterials] = useState();
  const [machineAmount, setMachineAmount] = useState({});
  const [machines, setMachines] = useState();
  const [hasAuthority, setHasAuthority] = useState(false);
  const token = localStorage.getItem("token")
    ? decode(JSON.parse(localStorage.getItem("token")))
    : "illegal";

  useEffect(() => {
    async function fetchData() {
      const projectInfo = await ProjectAPI.getProjectById(projectId);
      const userInfo = await EmployeeAPI.getEmployeeByUsername(token.sub);
      const materialInfo = await MaterialAPI.getMaterialsByProjectId(projectId);
      const machineInfo = await MachineryAPI.getMachineryByProjectId(projectId);
      setUser(userInfo.data);
      setProject(projectInfo.data);
      setMaterialAmount(materialInfo.data);
      setMachineAmount(machineInfo.data);
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
    const materialHolder = {
      steel: {
        name: "Steel",
        amount: materialAmount.steel ? materialAmount.steel : 0,
        icon: steelIcon,
      },
      brick: {
        name: "Brick",
        amount: materialAmount.brick ? materialAmount.brick : 0,
        icon: brickIcon,
      },
      lumber: {
        name: "Lumber",
        amount: materialAmount.lumber ? materialAmount.lumber : 0,
        icon: lumberIcon,
      },
      cement: {
        name: "Cement",
        amount: materialAmount.cement ? materialAmount.cement : 0,
        icon: cementIcon,
      },
    };
    setMaterials(materialHolder);
  }, [materialAmount]);

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
    const machineHolder = {
      crane: {
        name: "Crane",
        amount: machineAmount.crane ? machineAmount.crane : 0,
        icon: craneIcon,
      },
      drill: {
        name: "Drill",
        amount: machineAmount.drill ? machineAmount.drill : 0,
        icon: drillIcon,
      },
      forklift: {
        name: "Forklift",
        amount: machineAmount.forklift ? machineAmount.forklift : 0,
        icon: forkliftIcon,
      },
      ladder: {
        name: "Ladder",
        amount: machineAmount.ladder ? machineAmount.ladder : 0,
        icon: ladderIcon,
      },
    };
    setMachines(machineHolder);
  }, [machineAmount]);

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
        <ResourcesForSingleProjectPage
          title="Materials"
          resources={materials}
          projectId={projectId}
          hasAuthority={hasAuthority}
        ></ResourcesForSingleProjectPage>
      </div>
      <div>
        <ResourcesForSingleProjectPage
          title="Machines"
          resources={machines}
          projectId={projectId}
        ></ResourcesForSingleProjectPage>
      </div>
    </div>
  );
}

export default SingleProjectPage;
