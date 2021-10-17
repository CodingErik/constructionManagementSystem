import React, { useEffect, useState, useContext } from 'react';
import { EmployeeAPI } from '../api';
import MyProfileProjectsDisplay from '../components/myProfile/MyProfileProjectsDisplay';
import BriefTasksDisplay from '../components/home/BriefTasksDisplay';
import decode from 'jwt-decode';
import redirectIfTokenNull from '../components/RedirectHelper';
import DisplayBasicInformation from '../components/myProfile/DisplayBasicInformation';
import Spinner from '../components/spinner/Spinner';

export default function MyProfile() {
  redirectIfTokenNull();

  const [userInfo, setUserInfo] = useState({});
  const [userProject, setUserProject] = useState({});
  const [userTasks, setUserTask] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const username = localStorage.getItem('token')
    ? decode(JSON.parse(localStorage.getItem('token'))).sub
    : 'illegal';

  useEffect(() => {
    EmployeeAPI.getEmployeeByUsername(username)
      .then(({ data }) => {
        setUserInfo(data);
        setUserProject({ ...data?.project });
        setUserTask([...data?.taskList]);
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
          <div
            className='col-12 col-lg-6 mt-5'
            style={{
              objectFit: 'cover',
            }}
          >
            <img
              src={'https://source.unsplash.com/random/450x450'}
              className='rounded-circle img-fluid'
              alt='profile'
            ></img>
          </div>
          <div className='col-12 col-lg-6'>
            <DisplayBasicInformation
              userInfo={userInfo}
              updateUserBasicInformation={updateUserBasicInformation}
            />
          </div>
        </div>
        <div className='row mt-5'>
          <div className='col '>
            {userProject?.name ? (
              <MyProfileProjectsDisplay
                project={userProject}
              ></MyProfileProjectsDisplay>
            ) : (
              <h2>No Available projects... </h2>
            )}
          </div>
          <div className=' col '>
            {userTasks?.name ? (
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
