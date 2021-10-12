function AddModal({ allEmployeesNotInProject, projectId, handleAddEmployeeToProject, modalId, title }) {
    return (
        <div class="modal fade" id={modalId} tabindex="-1" aria-labelledby="addModal" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add {title}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-hover">
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
                                    <tr class="table-light">
                                        <th scope="row">{filteredEmployee.id}</th>
                                        <td>{filteredEmployee.name}</td>
                                        <td>{filteredEmployee.projectId === 0 ? "Unassigned" : filteredEmployee.projectId}</td>
                                        <td>{filteredEmployee.yearsOfExperience}</td>
                                        <td>${filteredEmployee.salary}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info" disabled={filteredEmployee.projectId > 0} onClick={(event) => { handleAddEmployeeToProject(filteredEmployee) }} data-bs-dismiss="modal">Add</button>
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