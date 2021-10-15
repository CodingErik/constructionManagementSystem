import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './home.css';

let columnBooleans = {
  id: false,
  name: false,
  status: false,
  project: false,
  startDate: false,
  deadline: false,
  description: false,
};

export default function BriefTasksDisplay({ originalTaskList, }) {
  const [taskList, setTaskList] = useState([]);


  useEffect(() => {
    setTaskList([...originalTaskList]);
  }, [originalTaskList]);

  const handleTaskColumnHeaderClick = (neededVariable, booleanVariable, methodTranslate) => {
    const sort_by = (neededField, reverse, primer) => {
      const getField = (obj, path) => (path.split('.').reduce((value, el) => value[el], obj))
      const key = primer
        ? function (x) {
          return primer(getField(x, neededField));
        }
        : function (x) {
          return getField(x, neededVariable);
        };
      reverse = !reverse ? 1 : -1;

      return function (a, b) {
        return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
      };
    };

    if (columnBooleans[booleanVariable]) {
      columnBooleans[booleanVariable] = false;
    } else {
      columnBooleans[booleanVariable] = true;
    }
    setTaskList(
      [...taskList].sort(
        sort_by(neededVariable, columnBooleans[booleanVariable], methodTranslate)
      )
    );
  }

  return (
    <div className='container'>
      <div className='row'>
        <h3>Task</h3>
      </div>

      <div
        className='table-responsive'
        style={{
          width: '100%',
          maxHeight: '400px',
          overflow: 'scroll',
        }}
      >
        <table className='table table-hover'>
          <thead>
            <tr>
              <th
                scope='col'
                onClick={() => handleTaskColumnHeaderClick('id', "id", parseInt)}
              >
                TaskId
              </th>
              <th
                scope='col'
                onClick={() => handleTaskColumnHeaderClick('name', "name", (a) => a.toUpperCase())}
              >
                Name
              </th>
              <th
                scope='col'
                onClick={() => handleTaskColumnHeaderClick('status', "status", (a) => a.toUpperCase())}
              >
                Status
              </th>
              <th
                scope='col'
                onClick={() => handleTaskColumnHeaderClick('project.name', "project", (a) => a.toUpperCase())}
              >
                Project Containing
              </th>

              <th
                scope='col'
                onClick={() => handleTaskColumnHeaderClick('deadline', "deadline", (a) => a.toUpperCase())}
              >
                Deadline
              </th>
            </tr>
          </thead>
          <tbody>
            {taskList.map((task) => (
              <tr className='table-active' key={task.id}>
                <th scope='row'>{task.id}</th>
                <td>{task.name}</td>
                <td>{task.status}</td>
                <td>{task.project ? task.project.name : task.projectId}</td>
                <td>{task.deadline}</td>
                <td>
                  <Link to={{ pathname: `/SingleTaskPage/${task.id}` }}>
                    <button type='button' className='btn btn-warning'>
                      View
                    </button>
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
