function EmployeeListTable({ employeeList }) {
    return (
        <div className="mt-3">
            <h3>Architects</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Employee Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Project Id</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    {employeeList.filter(employee => employee.title === "Architect").map(filteredEmployee => (
                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.projectId}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                            <td></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <h3>Employee</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Employee Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Project Id</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    {employeeList.filter(employee => employee.title === "Employee").map(filteredEmployee => (

                        <tr className="table-active" key={filteredEmployee.id}>
                            <th scope="row">{filteredEmployee.id}</th>
                            <td>{filteredEmployee.name}</td>
                            <td>{filteredEmployee.projectId}</td>
                            <td>{filteredEmployee.email}</td>
                            <td>{filteredEmployee.phoneNumber}</td>
                            <td></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default EmployeeListTable;