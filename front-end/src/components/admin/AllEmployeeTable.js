import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { BsFillTrashFill } from "react-icons/bs";
import { EmployeeAPI } from '../../api';

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

  useEffect(() => {
    const updatedEmployeeList = [...originalEmployeeList].filter(
      (nullProjects) => nullProjects.project === null
    );
    updatedEmployeeList.map(
      (project) => (project.project = { id: 0, name: '' })
    );
    setEmployeeList([...originalEmployeeList]);
  }, [originalEmployeeList]);

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

  const handleDeleteEmployeeById = (employeeId) => {
    EmployeeAPI.deleteEmployeeById(employeeId);
    setEmployeeList([...employeeList].filter(employee => employee.id !== employeeId));
  }

  return (
    <div className='container'>
      <h3>Employees Table</h3>
      <div className='table-res  table-responsive'>
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick('id', 'employeeId', parseInt)
                }
              >
                Employee Id
              </th>
              <th
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick('name', 'employeeName', (a) =>
                    a.toUpperCase()
                  )
                }
              >
                Name
              </th>
              <th
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'username',
                    'employeeUsername',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Username
              </th>
              <th
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'project.id',
                    'employeeProjectId',
                    parseInt
                  )
                }
              >
                Project Id
              </th>
              <th
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'project.name',
                    'employeeProjectName',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Project Name
              </th>
              <th
                className='col-1'
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
                className='col-1'
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
                className='col-1'
                onClick={() =>
                  handleProjectColumnHeaderClick(
                    'phoneNumber',
                    'employeePhoneNumber',
                    (a) => a.toUpperCase()
                  )
                }
              >
                Phone Number
              </th>
              <th
                className='col-1'
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
                className='col-1'
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
                className='col-1'
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
                className='col-1'
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
                    to={{ pathname: `/AdminSingleEmployeePage/${employee.id}` }}
                  >
                    <button type='button' className='btn btn-warning'>
                      View
                    </button>
                  </Link>
                </td>
                <td>
                  <button
                    type="button"
                    className="btn btn-danger"
                    onClick={() => handleDeleteEmployeeById(employee.id)}
                  >
                    <BsFillTrashFill></BsFillTrashFill>
                  </button>
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
