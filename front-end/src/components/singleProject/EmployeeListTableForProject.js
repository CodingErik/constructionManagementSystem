import AddModal from "./AddModal";
import { EmployeeAPI } from "../../api";
import { useState, useEffect } from "react";

function EmployeeListTableForProject({ projectId, hasAuthority }) {
    const [allEmployeesInProject, setAllEmployeesInProject] = useState([]);
    const [allUnassignedEmployees, setAllUnassignedEmployees] = useState([]);

    useEffect(() => {
        EmployeeAPI.getAllEmployees().then((response) => {
            setAllUnassignedEmployees([...response.data].filter(employee => parseInt(employee.projectId) === 0));
            setAllEmployeesInProject([...response.data].filter(employee => parseInt(employee.projectId) === parseInt(projectId)));
        });
    }, []);


    const handleAddEmployeeToProject = (employee) => {
        const updatedEmployee = {
            ...employee,
            projectId: projectId,
        };
        EmployeeAPI.putEmployee(updatedEmployee);
        setAllUnassignedEmployees((prevState) => [...prevState].filter(emp => emp.id != employee.id));
        setAllEmployeesInProject([...allEmployeesInProject, updatedEmployee]);
    };

    return (
        <div className="mt-3">
            <h3>Architects</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Employee Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    {allEmployeesInProject?.filter(employee => employee.title === "architect").map(filteredEmployee => (
                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <h3>Employees</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Employee Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    {allEmployeesInProject?.filter(employee => employee.title === "employee").map(filteredEmployee => (
                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                        </tr>
                    ))}
                    <tr>
                        <td colSpan="4" className="table">
                            <button type="button" disabled={!hasAuthority} className="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addEmployeeModal">Add Employee</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <AddModal title="employee" modalId="addEmployeeModal" allUnassignedEmployees={allUnassignedEmployees} projectId={projectId} handleAddEmployeeToProject={handleAddEmployeeToProject} />
        </div>
    )
}

export default EmployeeListTableForProject;