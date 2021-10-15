import { useState, useEffect } from 'react';
let columnBooleans = {
  employeeId: false,
  project: false,
  name: false,
  email: false,
  phoneNumber: false,
};

function EmployeeDisplayTable({ originalEmployeeList, title, filter }) {
  const [employeeList, setEmployeeList] = useState([]);
  useEffect(() => {
    setEmployeeList([...originalEmployeeList]);
  }, [originalEmployeeList]);

  const handleEmployeeColumnHeaderClick = (target) => {
    const sort_by = (field, reverse, primer) => {
      const key = primer
        ? function (x) {
            return primer(x[field]);
          }
        : function (x) {
            return x[field];
          };
      reverse = !reverse ? 1 : -1;

      return function (a, b) {
        return (a = key(a)), (b = key(b)), reverse * ((a > b) - (b > a));
      };
    };

    if (target === 'name') {
      if (columnBooleans.name) {
        columnBooleans.name = false;
      } else {
        columnBooleans.name = true;
      }
      setEmployeeList(
        [...employeeList].sort(
          sort_by('name', columnBooleans.name, (a) => a.toUpperCase())
        )
      );
    } else if (target === 'employeeId') {
      if (columnBooleans.employeeId) {
        columnBooleans.employeeId = false;
      } else {
        columnBooleans.employeeId = true;
      }
      setEmployeeList(
        [...employeeList].sort(
          sort_by('id', columnBooleans.employeeId, parseInt)
        )
      );
    } else if (target === 'email') {
      if (columnBooleans.email) {
        columnBooleans.email = false;
      } else {
        columnBooleans.email = true;
      }
      setEmployeeList(
        [...employeeList].sort(
          sort_by('email', columnBooleans.email, (a) => a.toUpperCase())
        )
      );
    } else if (target === 'phoneNumber') {
      if (columnBooleans.phoneNumber) {
        columnBooleans.phoneNumber = false;
      } else {
        columnBooleans.phoneNumber = true;
      }

      setEmployeeList(
        [...employeeList]
          .filter((e) => e.phoneNumber)
          .sort((a, b) => {
            return a.phoneNumber - b.phoneNumber;
          })
      );
    } else if (target === 'project') {
      if (columnBooleans.project) {
        columnBooleans.project = false;
      } else {
        columnBooleans.project = true;
      }

      setEmployeeList(
        [...employeeList]
          .filter((e) => e.project?.name)
          .sort((a, b) => {
            return a.project.name - b.project.name;
          })
      );
    }
  };
  return (
    <div className='row mt-3'>
      <h3 className='mb-3'>{title}</h3>
      <div
        className='table-responsive'
        style={{
          width: '100%',
          maxHeight: '400px',
          overflow: 'scroll',
        }}
      >
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th
                className='col-2'
                onClick={() => handleEmployeeColumnHeaderClick('employeeId')}
              >
                Employee Id
              </th>
              <th
                className='col-2'
                onClick={() => handleEmployeeColumnHeaderClick('name')}
              >
                Name
              </th>
              <th
                className='col-3'
                onClick={() => handleEmployeeColumnHeaderClick('project')}
              >
                Project
              </th>
              <th
                className='col-2'
                onClick={() => handleEmployeeColumnHeaderClick('email')}
              >
                Email
              </th>
              <th
                className='col-2'
                onClick={() => handleEmployeeColumnHeaderClick('phoneNumber')}
              >
                Phone Number
              </th>
            </tr>
          </thead>
          <tbody>
            {employeeList
              .filter(
                (employee) =>
                  employee?.title.toLowerCase() === filter.toLowerCase()
              )
              .map((filteredEmployee) => (
                <tr className='table-active' key={filteredEmployee.id}>
                  <th scope='row'>{filteredEmployee.id}</th>
                  <td>{filteredEmployee.name}</td>
                  <td>{filteredEmployee.project?.name || 'None'} </td>
                  <td>{filteredEmployee.email}</td>
                  <td>{filteredEmployee.phoneNumber || 'None'}</td>
                  <td></td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default EmployeeDisplayTable;
