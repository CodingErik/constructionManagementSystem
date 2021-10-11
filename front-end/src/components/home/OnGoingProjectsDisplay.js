function OnGoingProjectsDisplay({ projectList, handleProjectColumnHeaderClick }) {
    return (
        <div>
            <h3>Projects Table</h3>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col" onClick={()=> handleProjectColumnHeaderClick("ProjectId")}>Project Id</th>
                        <th scope="col" onClick={()=> handleProjectColumnHeaderClick("Name")}>Name</th>
                        <th scope="col" onClick={()=> handleProjectColumnHeaderClick("Status")}>Status</th>
                        <th scope="col" onClick={()=> handleProjectColumnHeaderClick("StartDate")}>Start Date</th>
                        <th scope="col" onClick={()=> handleProjectColumnHeaderClick("Deadline")}>Deadline</th>
                    </tr>
                </thead>
                <tbody>
                    {projectList.map((project) => (
                        <tr className="table-active" key={project.id}>
                            <th scope="row">{project.id}</th>
                            <td>{project.name}</td>
                            <td>{project.status}</td>
                            <td>{project.startDate}</td>
                            <td>{project.deadline}</td>
                            <td><button type="button" className="btn btn-warning">View</button></td>
                        </tr>
                    ))}

                </tbody>
            </table>
        </div>
    );
}

export default OnGoingProjectsDisplay;