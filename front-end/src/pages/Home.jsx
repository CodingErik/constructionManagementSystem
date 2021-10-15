import React, { useEffect, useState } from 'react';
import BriefProjectsDisplay from '../components/home/BriefProjectsDisplay';
import BriefTasksDisplay from '../components/home/BriefTasksDisplay';
import ProjectPieChart from '../components/home/ProjectPieChart';
import EmployeeListTable from '../components/home/EmployeeListTable';
import Materials from '../components/materials/Materials';
import Machinery from '../components/machinery/Machinery';
import {
  ProjectAPI,
  EmployeeAPI,
  TaskAPI,
  MaterialAPI,
  MachineryAPI,
} from '../api/index';
import redirectIfTokenNull from '../components/RedirectHelper';
import Spinner from '../components/Spinner';

function Home() {
  redirectIfTokenNull();
  const [projectList, setProjectList] = useState([]);
  const [statusCount, setStatusCount] = useState({
    inProgress: 0,
    cancelled: 0,
    completed: 0,
  });
  const [employeeList, setEmployeeList] = useState([]);
  const [taskList, setTaskList] = useState([]);
  const [materials, setMaterials] = useState();
  const [machinery, setMachinery] = useState();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    ProjectAPI.getAllProjects().then((response) => {
      setProjectList([...response.data]);
      response.data.forEach((project) => {
        if (project.status === 'in_progress') {
          setStatusCount((prevState) => ({
            ...prevState,
            inProgress: prevState.inProgress++,
          }));
        } else if (project.status === 'completed') {
          setStatusCount((prevState) => ({
            ...prevState,
            completed: prevState.completed++,
          }));
        } else if (project.status === 'cancelled') {
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
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });

    MaterialAPI.getAllMaterialsInventory().then((response) => {
      setMaterials(response.data);
    });

    MachineryAPI.getAllMachineryInventory().then((response) => {
      setMachinery(response.data);
    });

    setIsLoading(false);
  }, []);

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <div
      className='container mt-3 home'
      style={{
        overflowX: 'hidden',
      }}
    >
      <div className='row mb-3 mt-3'>
        <div className='col col-xl-6 ml-1 mr-1'>
          <BriefProjectsDisplay
            originalProjectLists={projectList}
          ></BriefProjectsDisplay>
        </div>
        <div className='col col-xl-6 ml-1 mr-1'>
          <ProjectPieChart statusCount={statusCount}></ProjectPieChart>
        </div>
      </div>

      <div className='row mb-3 mt-3'>
        <div className='col col-xl-6 ml-1 mr-1'>
          <div className='row mb-3'>
            <div className='row mt-3' style={{ minHeight: '445px' }}>
              <div className='col col-xl-6'>
                <Materials materials={materials} />
              </div>
              <div className='col col-xl-6'>
                <Machinery machinery={machinery} />
              </div>
            </div>
          </div>

          <div className='row mt-4'>
            <BriefTasksDisplay
              originalTaskList={taskList}
              projectIsNotANumber={true}
            ></BriefTasksDisplay>
          </div>
        </div>
        <div className='col col-xl-6 ml-1 mr-1'>
          <EmployeeListTable employeeList={employeeList} />
        </div>
      </div>
    </div>
  );
}

export default Home;
