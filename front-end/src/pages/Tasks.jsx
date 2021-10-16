import React, { useEffect, useState, useRef } from 'react';
import TasksTable from '../components/TasksTable';
import taskAPI from '../api/TaskAPI';
import projectAPI from '../api/ProjectAPI';
import employeeAPI from '../api/EmployeeAPI';
import './Tasks.css';
import redirectIfTokenNull from '../components/RedirectHelper';
import Spinner from '../components/spinner/Spinner';

export default function Tasks() {
  redirectIfTokenNull();
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');
  const [projectId, setProjectId] = useState();
  const [employeeId, setEmployeeId] = useState();
  const [name, setName] = useState();
  const [isLoading, setIsloading] = useState(true);

  const projectNameRef = useRef();
  const employeeNameRef = useRef();
  const taskNameRef = useRef();

  useEffect(() => {
    taskAPI
      .getAllTasks(
        projectId ? projectId : null,
        employeeId ? employeeId : null,
        name ? name : null
      )
      .then((res) => {
        setTasks(res.data);
      })
      .catch((error) => console.error(error));

    setIsloading(false);
  }, [projectId, employeeId, name]);

  function setFilters(event) {
    event.preventDefault();

    taskNameRef.current.value !== null && setName(taskNameRef.current.value);

    employeeNameRef.current.value !== null &&
      employeeAPI
        .getAllEmployees()
        .then((res) => {
          res.data.forEach((employee) => {
            if (employee.name === employeeNameRef.current.value) {
              setEmployeeId(employee.id);
            }
          });
        })
        .catch((error) => console.error(error));

    projectNameRef.current.value !== null &&
      projectAPI
        .getAllProjects(null, projectNameRef.current.value)
        .then((res) => {
          res.data.forEach((project) => {
            if (project.name === projectNameRef.current.value) {
              setProjectId(project.id);
            }
          });
        })
        .catch((error) => console.error(error));
  }

  function resetFilters(event) {
    event.preventDefault();

    projectNameRef.current.value = null;
    setProjectId(projectNameRef.current.value);

    employeeNameRef.current.value = null;
    setEmployeeId(employeeNameRef.current.valuell);

    taskNameRef.current.value = null;
    setName(taskNameRef.current.value);
  }

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <div className='container mt-3'>
      <div className='row'>
        <div className='statusFilterTasks col'>
          <div
            className='btn-group'
            role='group'
            aria-label='Basic radio toggle button group'
          >
            <p
              style={{ paddingTop: '13px', marginRight: '20px' }}
              className='textField'
            >
              Status :{' '}
            </p>
            <input
              onClick={() => setStatusFilter('all')}
              type='radio'
              className='btn-check'
              name='btnradio'
              id='allStatusFilter'
              autoComplete='off'
              defaultChecked=''
            />
            <label
              className='btn btn-outline-primary btn-sm'
              htmlFor='allStatusFilter'
            >
              All
            </label>
            <input
              onClick={() => setStatusFilter('in_progress')}
              type='radio'
              className='btn-check'
              name='btnradio'
              id='inProgressStatusFilter'
              autoComplete='off'
              defaultChecked=''
            />
            <label
              className='btn btn-outline-primary btn-sm'
              htmlFor='inProgressStatusFilter'
            >
              In Progress
            </label>
            <input
              onClick={() => setStatusFilter('completed')}
              type='radio'
              className='btn-check'
              name='btnradio'
              id='completedStatusFilter'
              autoComplete='off'
              defaultChecked=''
            />
            <label
              className='btn btn-outline-primary btn-sm'
              htmlFor='completedStatusFilter'
            >
              Completed
            </label>
          </div>
        </div>
      </div>

      <div className='row'>
        <div className='otherFiltersTasks col'>
          <div className='employeeNameFilter filter'>
            <p className='textField'>Task : </p>
            <input ref={taskNameRef} className='textInput' placeholder='Name' />
          </div>
          <div className='projectNameFilter filter'>
            <p className='textField'>Project : </p>
            <input
              ref={projectNameRef}
              className='textInput'
              placeholder='Name'
            />
          </div>
          <div className='employeeNameFilter filter'>
            <p className='textField'>Employee : </p>
            <input
              ref={employeeNameRef}
              className='textInput'
              placeholder='Name'
            />
          </div>
          <button
            className='filterButtonTasks'
            onClick={(event) => setFilters(event)}
          >
            Filter
          </button>
          <button
            className='filterButtonTasks'
            onClick={(event) => resetFilters(event)}
          >
            Reset
          </button>
        </div>
      </div>

      <div className='row'>
        <div className='col'>
          <TasksTable tasks={tasks} statusFilter={statusFilter} />
        </div>
      </div>
    </div>
  );
}
