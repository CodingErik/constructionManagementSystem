import { useState, useEffect } from 'react';
import decode from 'jwt-decode';
import { EmployeeAPI, TaskAPI } from '../../api';
import { useParams, Link, useHistory } from 'react-router-dom';
import AdminEditEmployeeInfoModal from './AdminEditEmployeeInfoModal';

function DisplayEmployeeInfo() {
  const [employeeInfo, setEmployeeInfo] = useState({});
  const { employeeId } = useParams();
  const [employeeTasks, setEmployeeTasks] = useState([]);
  const tokenAuthority = localStorage.getItem('token')
    ? decode(JSON.parse(localStorage.getItem('token'))).authorities
    : null;
  let history = useHistory();

  if (tokenAuthority !== 'admin') {
    history.push('/home');
  }

  useEffect(() => {
    EmployeeAPI.getEmployeeById(employeeId).then((response) => {
      setEmployeeInfo(response.data);
      setEmployeeTasks([...response.data.taskList]);
      console.log(response.data);
    });
  }, []);

  const handleRemoveUserFromTask = async (taskId) => {
    const taskInfo = await TaskAPI.getTaskById(taskId);
    const updatedTaskInfo = {
      ...taskInfo.data,
      projectId: taskInfo.data.project.id,
      employeeId: 0,
    };
    TaskAPI.putTask(updatedTaskInfo);
    setEmployeeTasks([...employeeTasks].filter((task) => task.id !== taskId));
    alert('User removed from task');
  };

  const handleBasicInformationUpdateSubmit = (updatedBasicInfo) => {
    EmployeeAPI.putEmployee(updatedBasicInfo);
    setEmployeeInfo(updatedBasicInfo);
  };

  return (
    <div className='container'>
      <h2>Employee Info </h2>
      <div className='row'>
        <div className='col-9'>
          <h4 className='text-start'>Employee Basic Information</h4>
        </div>
        <div className='col-3'>
          <button
            style={{ display: 'inline' }}
            type='button'
            className='btn btn-warning'
            data-bs-toggle='modal'
            data-bs-target='#editEmployeeInformationModalByAdmin'
          >
            Edit Employee Infomation
          </button>
          <AdminEditEmployeeInfoModal
            handleBasicInformationUpdateSubmit={
              handleBasicInformationUpdateSubmit
            }
            employeeInfo={employeeInfo}
            modalId='editEmployeeInformationModalByAdmin'
          ></AdminEditEmployeeInfoModal>
        </div>
      </div>
      <div className='list-group text-black'>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Id:</div>
            <div className='col-9'>{employeeInfo?.id}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Name:</div>
            <div className='col-9'>{employeeInfo.name}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Username:</div>
            <div className='col-9'>{employeeInfo.username}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Title:</div>
            <div className='col-9'>{employeeInfo.title}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Phone Number:</div>
            <div className='col-9'>{employeeInfo.phoneNumber}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Email:</div>
            <div className='col-9'>{employeeInfo.email}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Date Of Birth:</div>
            <div className='col-9'>{employeeInfo.dateOfBirth}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Salary:</div>
            <div className='col-9'>{employeeInfo.salary}</div>
          </div>
        </div>
        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>Years of Experience:</div>
            <div className='col-9'>{employeeInfo.yearsOfExperience}</div>
          </div>
        </div>

        <div className='list-group-item bg-transparent text-start '>
          <div className='row'>
            <div className='col-3'>User Since:</div>
            <div className='col-9'>{employeeInfo.userSince}</div>
          </div>
        </div>
      </div>
      <h2>Employee Project Info </h2>
      <div className='row mt-5'>
        <div className='col-9'>
          <h4 className='text-start'>{employeeInfo.name}'s Current Project</h4>
        </div>
        <div className='col-3'></div>
      </div>
      {employeeInfo.project && (
        <div className='list-group text-black'>
          <div className='list-group-item bg-transparent text-start'>
            <div className='row'>
              <div className='col-10'>
                <div className='row'>
                  <div className='col-3'>Project Id:</div>
                  <div className='col-9'>{employeeInfo.project?.id}</div>
                </div>
                <div className='row'>
                  <div className='col-3'>Project Name:</div>
                  <div className='col-9'>{employeeInfo.project?.name}</div>
                </div>
              </div>
              <div className='col-2'>
                <Link to={{ pathname: `/SingleProjectPage/${employeeInfo.project?.id}` }}>
                  <button type='button' class='btn btn-info'>
                    View Info
                  </button>
                </Link>
              </div>
            </div>
          </div>
        </div>
      )}
      {!employeeInfo.project && (
        <div className="text-start">{employeeInfo.name} Is Not Assigned To A Project</div>
      )}

      <h4 className="mt-4 text-start">{employeeInfo.name}'s Tasks For {employeeInfo.project?.name} Project</h4>
      {employeeTasks.map(task => (
        <div className="list-group text-black">
          <div className="list-group-item bg-transparent text-start" >
            <div className="row">
              <div className="col-8">
                <div className="row">
                  <div className="col-3">
                    Task Id:
                  </div>
                  <div className="col-9">
                    {task.id}
                  </div>
                </div>
                <div className="row">
                  <div className="col-3">
                    Task Name:
                  </div>
                  <div className="col-9">
                    {task.name}
                  </div>
                </div>
              </div>
              <div className="col-2">
                <button type="button" className="btn btn-danger" onClick={() => handleRemoveUserFromTask(task.id)}>Remove</button>
              </div>
              <div className="col-2">
                <Link to={{ pathname: `/SingleTaskPage/${task.id}` }} >
                  <button type="button" className="btn btn-info">View Info</button>
                </Link>
              </div>
            </div>
          </div>
        </div>
      ))}
      {employeeInfo.taskList?.length === 0 && (
        <div className="text-start">{employeeInfo.name} Is Not Assigned To A Project</div>
      )}
    </div >

  )
}

export default DisplayEmployeeInfo;
