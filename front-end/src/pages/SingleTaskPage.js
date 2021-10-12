import { useState, useEffect } from "react";
import { TaskAPI, ProjectAPI, EmployeeAPI } from "../api";
import { useParams } from "react-router-dom";
import TaskForm from "../components/singleTask/TaskForm";


function SingleTaskPage() {
    const { taskId } = useParams();
    const [task, setTask] = useState({});
    const [hasAuthority, setHasAuthority] = useState(true);
    const [projectList, setProjectList] = useState([]);
    const [employeeInThisProjectList, setEmployeeInThisProjectList] = useState([]);

    useEffect(() => {
        TaskAPI.getTaskById(taskId).then((response) => {
            setTask(response.data);
            EmployeeAPI.getAllEmployees().then((employeeResponse) => {
                setEmployeeInThisProjectList([...employeeResponse.data].filter(employee => employee.projectId == response.data.project.id));
            })
        });
        ProjectAPI.getAllProjects().then((response) => {
            setProjectList(response.data);
        });
    }, []);


    return (
        <div>
            <TaskForm task={task} hasAuthority={hasAuthority} projectList={projectList} employeeInThisProjectList={employeeInThisProjectList}></TaskForm>
        </div>
    )
}

export default SingleTaskPage;