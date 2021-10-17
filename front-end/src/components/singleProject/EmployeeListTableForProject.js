import AddModal from './AddEmployeeModal';
import { EmployeeAPI } from '../../api';
import { useState, useEffect } from 'react';

function EmployeeListTableForProject({ projectId, hasAuthority, allEmployees, updateAllEmployeeList }) {
  const [allEmployeesInProject, setAllEmployeesInProject] = useState([]);
  const [allUnassignedEmployees, setAllUnassignedEmployees] = useState([]);

  useEffect(() => {
    setAllUnassignedEmployees(
      [...allEmployees].filter((employee) => employee.project === null)
    );
    setAllEmployeesInProject(
      [...allEmployees].filter(
        (employee) => parseInt(employee.project?.id) === parseInt(projectId)
      )
    );
  }, [allEmployees]);

  const handleAddEmployeeToProject = async (employee) => {
    const updatedEmployee = {
      ...employee,
      projectId: projectId,
    };
    const update = await EmployeeAPI.putEmployee(updatedEmployee);
    alert("Employee Has Been Added To Project");
    updateAllEmployeeList(updatedEmployee);
  };

  return (
    <div className='mt-3'>
      <h3>Architects</h3>
      <div
        className='table-reponsive mt-3 mb-3'
        style={{
          width: '100%',
        }}
      >
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th className='col-1'>Id</th>
              <th className='col-3'>Name</th>
              <th className='col-3'>Email</th>
              <th className='col-4'>Phone Number</th>
            </tr>
          </thead>
          <tbody>
            {allEmployeesInProject
              ?.filter((employee) => employee.title?.toLowerCase() === 'architect')
              .map((filteredEmployee) => (
                <tr className='table-active' key={filteredEmployee.id}>
                  <th scope='row'>{filteredEmployee.id}</th>
                  <td>{filteredEmployee.name}</td>
                  <td>{filteredEmployee.email}</td>
                  <td>{filteredEmployee.phoneNumber}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>

      <h3 style={{ marginTop: "10%" }}>Employees</h3>
      <div
        className='table-reponsive'
        style={{
          width: '100%',
        }}
      >
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th className='col-1'>Id</th>
              <th className='col-3'>Name</th>
              <th className='col-3'>Email</th>
              <th className='col-4'>Phone Number</th>
            </tr>
          </thead>
          <tbody>
            {allEmployeesInProject
              ?.filter((employee) => employee.title?.toLowerCase() === 'employee')
              .map((filteredEmployee) => (
                <tr className='table-active' key={filteredEmployee.id}>
                  <th scope='row'>{filteredEmployee.id}</th>
                  <td>{filteredEmployee.name}</td>
                  <td>{filteredEmployee.email}</td>
                  <td>{filteredEmployee.phoneNumber}</td>
                </tr>
              ))}
          </tbody>
        </table>
        {hasAuthority && (
          <button
            type='button'
            disabled={!hasAuthority}
            className='btn btn-outline-warning'
            data-bs-toggle='modal'
            data-bs-target='#addEmployeeModal'
            style={{ width: "100%", marginTop: "3%" }}
          >
            Add Employee
          </button>
        )}
      </div>

      <AddModal
        title='employee'
        modalId='addEmployeeModal'
        allUnassignedEmployees={allUnassignedEmployees}
        projectId={projectId}
        handleAddEmployeeToProject={handleAddEmployeeToProject}
      />
    </div>
  );
}

export default EmployeeListTableForProject;
