import React, { useEffect, useState } from "react";
import BriefProjectsDisplay from "../components/home/BriefProjectsDisplay";
import BriefTasksDisplay from "../components/home/BriefTasksDisplay";
import ProjectPieChart from "../components/home/ProjectPieChart";
import EmployeeListTable from "../components/home/EmployeeListTable";
import Resources from "../components/resources/Resources";
import steelIcon from "../assets/steel.png";
import brickIcon from "../assets/brick.png";
import lumberIcon from "../assets/lumber.png";
import cementIcon from "../assets/cement.png";
import craneIcon from "../assets/crane.png";
import forkliftIcon from "../assets/forklift.png";
import ladderIcon from "../assets/ladder.png";
import drillIcon from "../assets/drill.png";
import {
  ProjectAPI,
  EmployeeAPI,
  TaskAPI,
  MaterialAPI,
  MachineryAPI,
} from "../api/index";
import redirectIfTokenNull from "../components/RedirectHelper";
import Spinner from "../components/spinner/Spinner";
import decode from "jwt-decode";

function Home() {
  redirectIfTokenNull();
  const authority = localStorage.getItem("token")
    ? decode(JSON.parse(localStorage.getItem("token"))).authorities
    : "illegal";
  const [projectList, setProjectList] = useState([]);
  const [statusCount, setStatusCount] = useState({
    inProgress: 0,
    cancelled: 0,
    completed: 0,
  });

  const [employeeList, setEmployeeList] = useState([]);
  const [taskList, setTaskList] = useState([]);
  const [materials, setMaterials] = useState({});
  const [machinery, setMachinery] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const [isVisible, setIsVisible] = useState("invisible");

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
    });

    TaskAPI.getAllTasks()
      .then((response) => {
        setTaskList(response.data);
      })
      .catch((error) => {
        console.log(error);
      });

    MaterialAPI.getWarehouseMaterialsInventory().then((response) => {
      const materialsHolder = {
        steel: {
          name: "Steel",
          amount: response.data?.steel,
          icon: steelIcon,
        },
        brick: {
          name: "Brick",
          amount: response.data?.brick,
          icon: brickIcon,
        },
        lumber: {
          name: "Lumber",
          amount: response.data?.lumber,
          icon: lumberIcon,
        },
        cement: {
          name: "Cement",
          amount: response.data?.cement,
          icon: cementIcon,
        },
      };
      setMaterials(materialsHolder);
    });

    MachineryAPI.getWarehouseMachineryInventory().then((response) => {
      const machineHolder = {
        crane: {
          name: "Crane",
          amount: response.data?.crane,
          icon: craneIcon,
        },
        forklift: {
          name: "Forklift",
          amount: response.data?.forklift,
          icon: forkliftIcon,
        },
        ladder: {
          name: "Ladder",
          amount: response.data?.ladder,
          icon: ladderIcon,
        },
        drill: {
          name: "Drill",
          amount: response.data?.drill,
          icon: drillIcon,
        },
      };
      setMachinery(machineHolder);
    });

    setIsLoading(false);
  }, []);

  if (isLoading) {
    return <Spinner />;
  }

  async function refillMaterials(event) {
    event.preventDefault();

    setIsVisible("visible");

    setTimeout(() => {
      setIsVisible("invisible");
    }, 3000);

    const refillMaterials =
      await MaterialAPI.refillWarehouseMaterialsInventory();
    const updateMaterialWareHouse =
      await MaterialAPI.getWarehouseMaterialsInventory();

    const materialsHolder = {
      steel: {
        name: "Steel",
        amount: updateMaterialWareHouse.data?.steel,
        icon: steelIcon,
      },
      brick: {
        name: "Brick",
        amount: updateMaterialWareHouse.data?.brick,
        icon: brickIcon,
      },
      lumber: {
        name: "Lumber",
        amount: updateMaterialWareHouse.data?.lumber,
        icon: lumberIcon,
      },
      cement: {
        name: "Cement",
        amount: updateMaterialWareHouse.data?.cement,
        icon: cementIcon,
      },
    };

    setMaterials(materialsHolder);
  }

  return (
    <div
      className="container mt-3 home"
      style={{
        overflowX: "hidden",
      }}
    >
      <div className="row mb-3 mt-3">
        <div className="col col-xl-6 ml-1 mr-1">
          <BriefProjectsDisplay
            originalProjectLists={projectList}
          ></BriefProjectsDisplay>
        </div>
        <div className="col col-xl-6 ml-1 mr-1">
          <ProjectPieChart statusCount={statusCount}></ProjectPieChart>
        </div>
      </div>

      <div className="row mb-3 mt-3">
        <div className="col col-xl-6 ml-1 mr-1">
          <div className="row mb-3">
            <div className="row mt-3" style={{ minHeight: "445px" }}>
              <div className="col col-xl-6">
                <Resources
                  resources={materials}
                  title="Materials"
                  denominator="/1000 lbs"
                  denominatorValue="1000"
                />
                {authority === "admin" && (
                  <button
                    type="button"
                    className="btn btn-danger btn-block mt-1 mb-5"
                    style={{ fontWeight: "800" }}
                    onClick={refillMaterials}
                    disabled={authority !== "admin"}
                  >
                    Refill Materials (Admin Only!)
                  </button>
                )}
              </div>
              <div className="col col-xl-6">
                <Resources
                  resources={machinery}
                  title="Machines"
                  denominator="/30 units"
                  denominatorValue="30"
                />
                <div className={`messageTooltip ${isVisible}`}>
                  Request fullfiled!
                </div>
              </div>
            </div>
          </div>

          <div className="row mt-4">
            <BriefTasksDisplay
              originalTaskList={taskList}
              projectIsNotANumber={true}
            ></BriefTasksDisplay>
          </div>
        </div>
        <div className="col col-xl-6 ml-1 mr-1">
          <EmployeeListTable employeeList={employeeList} />
        </div>
      </div>
    </div>
  );
}

export default Home;
