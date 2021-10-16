import React, { useEffect, useState, useContext } from 'react';
import { EmployeeAPI } from '../api';
import BriefProjectsDisplay from '../components/home/BriefProjectsDisplay';
import BriefTasksDisplay from '../components/home/BriefTasksDisplay';
import decode from 'jwt-decode';
import redirectIfTokenNull from '../components/RedirectHelper';
import DisplayBasicInformation from '../components/myProfile/DisplayBasicInformation';
import Spinner from '../components/spinner/Spinner';

export default function MyProfile() {
  redirectIfTokenNull();

  const [userInfo, setUserInfo] = useState({});
  const [userProjects, setUserProject] = useState([]);
  const [userTasks, setUserTask] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const username = localStorage.getItem('token')
    ? decode(JSON.parse(localStorage.getItem('token'))).sub
    : 'illegal';

  useEffect(() => {
    EmployeeAPI.getEmployeeByUsername(username)
      .then(({ data }) => {
        setUserInfo(data);
        setUserProject({ ...data.project });
        setUserTask([...data.taskList]);
      })
      .catch((error) => {
        console.error(error);
      });

    setIsLoading(false);
  }, [username]);

  const updateUserBasicInformation = (updatedUserInfo) => {
    EmployeeAPI.putEmployee(updatedUserInfo);
    setUserInfo(updatedUserInfo);
  };
  if (isLoading) {
    return <Spinner />;
  }

  return (
    <div className='container'>
      <div className='mt-5'>
        <div className='row'>
          <div className='col col-lg-6'>
            <img
              src='https://source.unsplash.com/random/550x550'
              className='rounded-circle'
              alt='profile'
            ></img>
          </div>
          <DisplayBasicInformation
            userInfo={userInfo}
            updateUserBasicInformation={updateUserBasicInformation}
          />
        </div>
        <div className='row mt-5'>
          <div className='col '>
            {userProjects.id ? (
              <BriefProjectsDisplay
                originalProjectLists={userProjects}
              ></BriefProjectsDisplay>
            ) : (
              <h2>No Available projects... </h2>
            )}
          </div>
          <div className=' col '>
            {userTasks.length ? (
              <BriefTasksDisplay
                originalTaskList={userTasks}
                projectIsNotANumber={false}
              ></BriefTasksDisplay>
            ) : (
              <h2>No Available tasks... </h2>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
