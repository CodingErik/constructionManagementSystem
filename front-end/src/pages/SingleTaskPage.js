import { useState, useEffect } from 'react';
import { TaskAPI, ProjectAPI, EmployeeAPI } from '../api';
import { useParams } from 'react-router-dom';
import TaskForm from '../components/singleTask/TaskForm';
import decode from 'jwt-decode';
import redirectIfTokenNull from '../components/RedirectHelper';

function SingleTaskPage() {
  redirectIfTokenNull();
  const { taskId } = useParams();
  const [task, setTask] = useState({});
  const [hasAuthority, setHasAuthority] = useState(false);
  const [projectList, setProjectList] = useState([]);
  const [employeeInThisProjectList, setEmployeeInThisProjectList] = useState(
    []
  );
  const token = decode(JSON.parse(localStorage.getItem('token')));

  useEffect(() => {
    TaskAPI.getTaskById(taskId).then((response) => {
      setTask(response.data);
      EmployeeAPI.getAllEmployees().then((employeeResponse) => {
        setEmployeeInThisProjectList(
          [...employeeResponse.data].filter(
            (employee) => employee.projectId == response.data.project.id
          )
        );
      });
    });
    ProjectAPI.getAllProjects().then((response) => {
      setProjectList(response.data);
    });
    setHasAuthority(
      token.authorities.toLowerCase() === 'architect' ? true : false
    );
  }, []);

  return (
    <div className='container'>
      <TaskForm
        task={task}
        hasAuthority={hasAuthority}
        projectList={projectList}
        employeeInThisProjectList={employeeInThisProjectList}
      ></TaskForm>
    </div>
  );
}

export default SingleTaskPage;
