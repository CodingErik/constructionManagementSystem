import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

let columnBooleans = {
  employeeId: false,
  employeeProjectId: false,
  employeeProjectName: false,
  employeeName: false,
  employeeUsername: false,
  employeeEmail: false,
  employeeTitle: false,
  employeePhoneNumber: false,
  employeeSalary: false,
  employeeDateOfBirth: false,
  employeeUserSince: false,
  employeeYearsOfExperience: false,
};

function AllEmployeeTable({ originalEmployeeList }) {
  const [employeeList, setEmployeeList] = useState([]);
  console.log(originalEmployeeList);

  useEffect(() => {
    setEmployeeList([...originalEmployeeList]);
  }, [originalEmployeeList]);

  const handleProjectColumnHeaderClick = (
    targetVariable,
    booleanVariable,
    methodTranslate
  ) => {
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

    if (columnBooleans[booleanVariable]) {
      columnBooleans[booleanVariable] = false;
    } else {
      columnBooleans[booleanVariable] = true;
    }
    setEmployeeList(
      [...employeeList].sort(
        sort_by(
          targetVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
  };

  const handleProjectColumnHeaderLeadClick = (
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
    setEmployeeList(
      [...employeeList].sort(
        sort_by(
          neededVariable,
          columnBooleans[booleanVariable],
          methodTranslate
        )
      )
    );
  };

  return (
    <div className='container'>
      <h3>Employees Table</h3>
      <div className='table-responsive'>
        <table className='table table-hover'>
          <thead>
            <tr>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick('id', 'employeeId', parseInt)
                }
              >
                Employee Id
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick('name', 'employeeName', (a) =>
                    a.toUpperCase()
                  )
                }
              >
                Name
              </th>
              <th
                scope='col'
                onClick={() => handleProjectColumnHeaderClick('username')}
              >
                Username
              </th>
              <th
                scope='col'
                // onClick={() =>
                //   handleProjectColumnHeaderLeadClick(
                //     'project.id',
                //     'employeeProjectId',
                //     parseInt
                //   )
                // }
              >
                Project Id
              </th>
              <th
                scope='col'
                // onClick={() =>
                //   handleProjectColumnHeaderLeadClick('project.name')
                // }
              >
                Project Name
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'title',
                    'employeeTitle',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Title
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'email',
                    'employeeEmail',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Email
              </th>
              <th
                scope='col'
                // onClick={() =>
                //   handleProjectColumnHeaderClick(
                //     'phoneNumber',
                //     'employeePhoneNumber',
                //     (a) => a.toUpperCase()
                //   )
                // }
              >
                Phone Number
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'salary',
                    'employeeSalary',
                    parseInt
                  )
                }
              >
                Salary
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'dateOfBirth',
                    'employeeDateOfBirth',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Date Of Birth
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'yearsOfExperience',
                    'employeeYearsOfExperience',
                    parseInt
                  )
                }
              >
                Years Of Experience
              </th>
              <th
                scope='col'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'userSince',
                    'employeeUserSince',
                    parseInt
                  )
                }
              >
                User Since
              </th>
            </tr>
          </thead>
          <tbody>
            {employeeList.map((employee) => (
              <tr className='table-active' key={employee.id}>
                <th scope='row'>{employee.id}</th>
                <td>{employee.name}</td>
                <td>{employee.username}</td>
                <td>{employee.project ? employee.project.id : 0}</td>
                <td>
                  {employee.project ? employee.project.name : 'Unassigned'}
                </td>
                <td>{employee.title}</td>
                <td>{employee.email}</td>
                <td>{employee.phoneNumber}</td>
                <td>{employee.salary}</td>
                <td>{employee.dateOfBirth}</td>
                <td>{employee.yearsOfExperience}</td>
                <td>{employee.userSince}</td>
                <td>
                  <Link
                    to={{
                      pathname: `/AdminSingleEmployeePage/${employee.id}`,
                    }}
                  >
                    <button type='button' className='btn btn-warning'>
                      Edit
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

export default AllEmployeeTable;
