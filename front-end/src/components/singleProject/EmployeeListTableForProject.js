import AddModal from "./AddModal";
import { EmployeeAPI } from "../../api";
import { useState, useEffect } from "react";

function EmployeeListTableForProject({ projectId }) {
    const [allEmployeesInProject, setAllEmployeesInProject] = useState([]);
    const [allEmployeesNotInProject, setAllEmployeesNotIntProject] = useState([]);

    useEffect(() => {
        EmployeeAPI.getAllEmployees().then((response) => {
            setAllEmployeesInProject([...response.data].filter(employee => employee.projectId == projectId));
            setAllEmployeesNotIntProject([...response.data].filter(employee => employee.projectId != projectId));
        });
    }, []);

    const handleAddEmployeeToProject = (employee) => {
        const updatedEmployee = {
            ...employee,
            projectId: projectId,
        };
        EmployeeAPI.putEmployee(updatedEmployee);
        setAllEmployeesNotIntProject((prevState) => [...prevState].filter(emp => emp.id != employee.id));
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
                    {allEmployeesInProject?.filter(employee => employee.title === "Architect").map(filteredEmployee => (
                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                        </tr>
                    ))}
                    <tr>
                        <td colspan="4" class="table">
                            <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addArchitectModal">Add Architect</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <AddModal title="Architect" modalId="addArchitectModal" allEmployeesNotInProject={allEmployeesNotInProject} projectId={projectId} handleAddEmployeeToProject={handleAddEmployeeToProject} />

            <h3>Employee</h3>
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
                    {allEmployeesInProject?.filter(employee => employee.title === "Employee").map(filteredEmployee => (
                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                        </tr>
                    ))}
                    <tr>
                        <td colspan="4" class="table">
                            <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addEmployeeModal">Add Employee</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <AddModal title="Employee" modalId="addEmployeeModal" allEmployeesNotInProject={allEmployeesNotInProject} projectId={projectId} handleAddEmployeeToProject={handleAddEmployeeToProject} />
        </div>
    )
}

export default EmployeeListTableForProject;