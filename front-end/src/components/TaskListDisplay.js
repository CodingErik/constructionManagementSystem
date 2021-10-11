import React from 'react'

export default function TaskListDisplay({ taskList, handleTaskColumnHeaderClick }) {

    return (
        <div>
            <h3>Task Table</h3>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("TaskId")} >TaskId</th>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("Task")} >Task</th>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("Status")} >Status</th>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("StartDate")} >StartDate</th>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("Deadline")} >Deadline</th>
                            <th scope="col" onClick={()=> handleTaskColumnHeaderClick("description")} >description</th>
                            {/* <th scope="col" >employee Id</th> */}
                            {/* <th scope="col" >projecdt Id</th> */}
                        </tr>
                    </thead>
                    <tbody>
                        {taskList[0].map((t) => (
                            <tr className="table-active" key={t.id}>
                                <th scope="row">{t.id}</th>
                                <td>{t.name}</td>
                                <td>{t.status}</td>
                                <td>{t.startDate}</td>
                                <td>{t.deadline}</td>
                                <td>{t.description}</td>
                                {/* <td>{t.employeeId}</td> */}
                                {/* <td>{t.projectId}</td> */}
                                <td><button type="button" className="btn btn-warning">View</button></td>
                            </tr>
                        ))}

                    </tbody>
                </table>
            </div>
    );
}




// onClick={()=> handleProjectColumnHeaderClick("ProjectId")}
// onClick={()=> handleProjectColumnHeaderClick("Name")}
// onClick={()=> handleProjectColumnHeaderClick("Status")}
// onClick={()=> handleProjectColumnHeaderClick("StartDate")}
// onClick={()=> handleProjectColumnHeaderClick("Deadline")}