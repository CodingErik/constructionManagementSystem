import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function ProjectsTable(props) {
  const [projects, setProjects] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');

  useEffect(() => {
    setProjects(props.projects);
    setStatusFilter(props.statusFilter);
  }, [props]);

  return (
    <div className='table-responsive'>
      <table className='table table-hover m-auto'>
        <thead>
          <tr>
            <th className='col-1'>Id</th>
            <th className='col-1'>Name</th>
            <th className='col-1'>Deadline</th>
            <th className='col-1'>Start</th>
            <th className='col-1'>Room</th>
            <th className='col-1'>Plumbing</th>
            <th className='col-1'>Electric</th>
            <th className='col-1'>Material</th>
            <th className='col-1'>Labor</th>
            <th className='col-1'>Total</th>
            <th className='col-1'>Status</th>
          </tr>
        </thead>
        <tbody>
          {statusFilter === 'all' &&
            projects.map((project) => {
              return (
                <tr key={project.id}>
                  <th scope='row'>{project.id}</th>
                  <td>{project.name}</td>
                  <td>{project.deadline}</td>
                  <td>{project.startDate}</td>
                  <td>{project.roomType}</td>
                  <td>{project.plumbing ? '✔️' : '❌'}</td>
                  <td>{project.electric ? '✔️' : '❌'}</td>
                  <td>${project.materialBudget.toLocaleString()}</td>
                  <td>${project.laborBudget.toLocaleString()}</td>
                  <td>${project.totalBudget.toLocaleString()}</td>
                  <td className={`${project.status} projectStatus`}>
                    {project.status}
                  </td>
                  <td>
                    <Link to={{ pathname: `/SingleProjectPage/${project.id}` }}>
                      <button type='button' className='btn btn-warning'>
                        View
                      </button>
                    </Link>
                  </td>
                </tr>
              );
            })}

          {statusFilter === 'completed' &&
            projects.map((project) => {
              return (
                project.status === 'completed' && (
                  <tr key={project.id}>
                    <th scope='row'>{project.id}</th>
                    <td>{project.name}</td>
                    <td>{project.deadline}</td>
                    <td>{project.startDate}</td>
                    <td>{project.roomType}</td>
                    <td>{project.plumbing.toString()}</td>
                    <td>{project.electric.toString()}</td>
                    <td>${project.materialBudget.toLocaleString()}</td>
                    <td>${project.laborBudget.toLocaleString()}</td>
                    <td>${project.totalBudget.toLocaleString()}</td>
                    <td className={`${project.status} projectStatus`}>
                      {project.status}
                    </td>
                    <td>
                      <Link
                        to={{ pathname: `/SingleProjectPage/${project.id}` }}
                      >
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
