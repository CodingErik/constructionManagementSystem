function AddModal({ allEmployeesNotInProject, projectId, handleAddEmployeeToProject, modalId, title }) {
    return (
        <div className="modal fade" id={modalId} tabIndex="-1" aria-labelledby="addModal" aria-hidden="true">
            <div className="modal-dialog modal-lg">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title" id="exampleModalLabel">Add {title}</h5>
                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">{title} Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Current Project</th>
                                    <th scope="col">Years of Exprience</th>
                                    <th scope="col">Salary</th>
                                </tr>
                            </thead>
                            <tbody>
                                {allEmployeesNotInProject.filter((employee) => employee.title === title && employee.projectId !== projectId).map((filteredEmployee) => (
                                    <tr className="table-light" key={filteredEmployee.id}>
                                        <th scope="row">{filteredEmployee.id}</th>
                                        <td>{filteredEmployee.name}</td>
                                        <td>{filteredEmployee.projectId === 0 ? "Unassigned" : filteredEmployee.projectId}</td>
                                        <td>{filteredEmployee.yearsOfExperience}</td>
                                        <td>${filteredEmployee.salary}</td>
                                        <td>
                                            <button type="button" className="btn btn-outline-info" disabled={filteredEmployee.projectId > 0} onClick={(event) => { handleAddEmployeeToProject(filteredEmployee) }} data-bs-dismiss="modal">Add</button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddModal;