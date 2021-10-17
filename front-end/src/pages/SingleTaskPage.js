import { useState, useEffect } from 'react';
import { TaskAPI,  EmployeeAPI } from '../api';
import { useParams } from 'react-router-dom';
import TaskForm from '../components/singleTask/TaskForm';
import decode from 'jwt-decode';
import redirectIfTokenNull from '../components/RedirectHelper';

function SingleTaskPage() {
  redirectIfTokenNull();
  const token = localStorage.getItem('token')
  ? decode(JSON.parse(localStorage.getItem('token')))
  : 'illegal';
  
  const { taskId } = useParams();
  const [task, setTask] = useState({});
  const [hasAuthority, setHasAuthority] = useState(false);


  useEffect(() => {
    async function fetchData() {
      const taskInfo = await TaskAPI.getTaskById(taskId);
      const userInfo = await EmployeeAPI.getEmployeeByUsername(token.sub);

      setTask(taskInfo.data);

      if (
        (token.authorities.toLowerCase() === 'architect' &&
          userInfo.data.project?.id === taskInfo.data.project?.id) ||
        token.authorities.toLowerCase() === 'admin'
      ) {
        setHasAuthority(true);
      } else {
        setHasAuthority(false);
      }
    }
    fetchData();
  }, []);

  return (
    <div className='container'>
      <TaskForm
        task={task}
        hasAuthority={hasAuthority}
      ></TaskForm>
    </div>
  );
}

export default SingleTaskPage;
