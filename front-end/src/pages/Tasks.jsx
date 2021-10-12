import React, { useEffect, useState, useRef } from 'react';
import TasksTable from '../components/TasksTable';
import taskAPI from '../api/TaskAPI';
import projectAPI from '../api/ProjectAPI';
import employeeAPI from '../api/EmployeeAPI';

export default function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');
  const [projectId, setProjectId] = useState();
  const [employeeId, setEmployeeId] = useState();
  const [name, setName] = useState();

  const projectNameRef = useRef();
  const employeeNameRef = useRef();

  useEffect(() => {
    taskAPI.getAllTasks(projectId ? projectId : null, employeeId ? employeeId : null, name ? name : null)
      .then(res => {
        setTasks(res.data);
      })
      .catch(error => console.error(error));
  }, [projectId, employeeId, name]);

  function setFilters(event) {
    event.preventDefault();

    employeeAPI.getAllEmployees()
      .then(res => {
        res.data.forEach(employee => {
          if(employee.name === employeeNameRef.current.value){
            setEmployeeId(employee.id);
          }
        })
      })
      .catch(error => console.error(error));

    projectAPI.getAllProjects(null, projectNameRef.current.value)
      .then(res => {
        setProjectId(res.data[0].id);
      })
      .catch(error => console.error(error));
  }

  function resetFilters(event) {
    event.preventDefault();
    projectNameRef.current.value = null;
    setProjectId(null);
    employeeNameRef.current.value = null;
    setEmployeeId(null);
  }

  return (
    <div>
      <div className='statusFilter'>
        <p style={{ paddingTop: '13px' }}>Status : </p>
        <div
          className='btn-group'
          role='group'
          aria-label='Basic radio toggle button group'
        >
          <input
            onClick={() => setStatusFilter('all')}
            type='radio'
            className='btn-check'
            name='btnradio'
            id='allStatusFilter'
            autoComplete='off'
            defaultChecked=''
          />
          <label className='btn btn-outline-primary' htmlFor='allStatusFilter'>
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
            className='btn btn-outline-primary'
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
            className='btn btn-outline-primary'
            htmlFor='completedStatusFilter'
          >
            Completed
          </label>
        </div>
      </div>

      <div className='otherFilters'>
        <div className='projectNameFilter filter'>
          <p>Project Name</p>
          <input ref={projectNameRef} />
        </div>
        <div className='employeeNameFilter filter'>
          <p>Employee Name</p>
          <input ref={employeeNameRef} />
        </div>

        <button className='filterButton' onClick={(event) => setFilters(event)}>
          Filter
        </button>
        <button
          className='filterButton'
          onClick={(event) => resetFilters(event)}
        >
          Reset
        </button>
      </div>

      <TasksTable tasks={tasks} statusFilter={statusFilter}/>
    </div>
  );
}
