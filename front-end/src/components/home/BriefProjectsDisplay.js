import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

let columnBooleans = {
  projectId: false,
  name: false,
  status: false,
  startDate: false,
  deadline: false,
};

function BriefProjectsDisplay({ originalProjectLists }) {
  const [projectList, setProjectList] = useState([]);

  useEffect(() => {
    setProjectList([...originalProjectLists]);
  }, [originalProjectLists]);

  const handleProjectColumnHeaderClick = (
    neededVariable,
    booleanVariable,
    methodTranslate
  ) => {
    const sort_by = (neededField, reverse, primer) => {
      const getField = (obj, path) =>
        path.split('.').reduce((value, el) => value[el], obj);
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
    setProjectList(
      [...projectList].sort(
        sort_by(
          neededVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
  };

  return (
    <div>
      <h3>Projects</h3>
      <div
        className='table-responsive'
        style={{
          maxHeight: '400px',
          overflowY: 'scroll',
        }}
      >
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th
                className='col-2'
                onClick={() =>
                  handleProjectColumnHeaderClick('id', 'projectId', parseInt)
                }
              >
                Project Id
              </th>
              <th
                className='col-2'
                onClick={() =>
                  handleProjectColumnHeaderClick('name', 'name', (a) =>
                    a.toUpperCase()
                  )
                }
              >
                Name
              </th>
              <th
                className='col-2'
                onClick={() =>
                  handleProjectColumnHeaderClick('status', 'status', (a) =>
                    a.toUpperCase()
                  )
                }
              >
                Status
              </th>
              <th
                className='col-2'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'startDate',
                    'startDate',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Start Date
              </th>
              <th
                className='col-2'
                onClick={() =>
                  handleProjectColumnHeaderClick('deadline', 'deadline', (a) =>
                    a.toUpperCase()
                  )
                }
              >
                Deadline
              </th>
            </tr>
          </thead>
          <tbody>
            {projectList.map((project) => (
              <tr className='table-active' key={project.id}>
                <th scope='row'>{project.id}</th>
                <td>{project.name}</td>
                <td>{project.status}</td>
                <td>{project.startDate}</td>
                <td>{project.deadline}</td>
                <td>
                  <Link to={{ pathname: `/SingleProjectPage/${project.id}` }}>
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

export default BriefProjectsDisplay;
