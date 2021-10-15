import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '../pages/Tasks.css';

export default function TasksTable(props) {
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');

  useEffect(() => {
    setTasks(props.tasks);
    setStatusFilter(props.statusFilter);
  }, [props]);

  return (
    <div className='table-responsive mb-5'>
      <table
        className='table table-hover table-striped m-auto'
        style={{
          verticalAlign: 'baseline',
          width: '100%',
        }}
      >
        <thead>
          <tr>
            <th className='col-1'>Id</th>
            <th className='col-2'>Name</th>
            <th className='col-1'>Deadline</th>
            <th className='col-1'>Start</th>
            <th className='col-1'>Project id</th>
            <th className='col-2'>Project Name</th>
            <th className='col-1'>Employee id</th>
            <th className='col-2'>Employee Name</th>
            <th className='col-1'>Status</th>
          </tr>
        </thead>
        <tbody>
          {statusFilter === 'all' &&
            tasks.map((task) => {
              return (
                <tr key={task.id}>
                  <th scope='row'>{task.id}</th>
                  <td>{task.name}</td>
                  <td>{task.deadline}</td>
                  <td>{task.startDate}</td>
                  <td>{task.project.id} </td>
                  <td>{task.project.name} </td>
                  <td>{task.employee.id}</td>
                  <td>{task.employee.name}</td>
                  <td className={`${task.status} taskStatus`}>{task.status}</td>
                  <td>
                    <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                      <button type='button' className='btn btn-warning'>
                        View
                      </button>
                    </Link>
                  </td>
                </tr>
              );
            })}

          {statusFilter === 'in_progress' &&
            tasks.map((task) => {
              return (
                task.status === 'in_progress' && (
                  <tr key={task.id}>
                    <th scope='row'>{task.id}</th>
                    <td>{task.name}</td>
                    <td>{task.deadline}</td>
                    <td>{task.startDate}</td>
                    <td>{task.project.id} </td>
                    <td>{task.project.name} </td>
                    <td>{task.employee.id}</td>
                    <td>{task.employee.name}</td>
                    <td className={`${task.status} taskStatus`}>
                      {task.status}
                    </td>
                    <td>
                      <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                        <button type='button' className='btn btn-warning'>
                          View
                        </button>
                      </Link>
                    </td>
                  </tr>
                )
              );
            })}

          {statusFilter === 'completed' &&
            tasks.map((task) => {
              return (
                task.status === 'completed' && (
                  <tr key={task.id}>
                    <th scope='row'>{task.id}</th>
                    <td>{task.name}</td>
                    <td>{task.deadline}</td>
                    <td>{task.startDate}</td>
                    <td>{task.project.id} </td>
                    <td>{task.project.name} </td>
                    <td>{task.employee.id}</td>
                    <td>{task.employee.name}</td>
                    <td className={`${task.status} taskStatus`}>
                      {task.status}
                    </td>
                    <td>
                      <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                        <button type='button' className='btn btn-warning'>
                          View
                        </button>
                      </Link>
                    </td>
                  </tr>
                )
              );
            })}
        </tbody>
      </table>
    </div>
  );
}
