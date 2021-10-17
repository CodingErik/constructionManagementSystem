import { useEffect, useState } from 'react';
import { EmployeeAPI, TaskAPI } from '../../api';
import AddTaskModal from './AddTaskModal';

function TaskListTableForProject({ projectId, projectName, hasAuthority, allEmployees }) {
  const [taskList, setTaskList] = useState([]);
  const [availableEmployeesInProject, setAvailableEmployeesInProject] =
    useState([]);

  useEffect(() => {
    TaskAPI.getTaskByProjectId(projectId).then((response) => {
      setTaskList(response.data);
    });
    setAvailableEmployeesInProject([...allEmployees].filter((employee) => (parseInt(employee.project?.id) === parseInt(projectId))));
  }, [allEmployees]);


  const handleAddTaskToProject = (newTask) => {
    TaskAPI.addTask(newTask);
    TaskAPI.getTaskByProjectId(projectId).then((response) => {
      setTaskList(response.data)
    });
  };

  return (
    <div className='container'>
      <h3>Tasks</h3>
      <div
        className='table-responsive mb-3'
        style={{
          width: '100%',
          marginTop: "5.2%"
        }}
      >
        <table className='table table-hover m-auto'>
          <thead>
            <tr>
              <th className='col-1'>Id</th>
              <th className='col-4'>Name</th>
              <th className='col-7'>Employee Assigned</th>
              <th className='col-4'>Status</th>
            </tr>
          </thead>
          <tbody>
            {taskList.map((task) => (
              <tr className='table-active' key={task.id}>
                <th scope='row'>{task.id}</th>
                <td>{task.name}</td>
                <td>{task.employee.name}</td>
                <td>{task.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {hasAuthority && (
          <button
            type='button'
            className='btn btn-outline-warning'
            data-bs-toggle='modal'
            data-bs-target='#addTaskModal'
            disabled={!hasAuthority}
            style={{ width: "100%", marginTop: "3%" }}
          >
            Add Task
          </button>
        )}
      </div>
      <AddTaskModal
        modalId='addTaskModal'
        availableEmployeesInProject={availableEmployeesInProject}
        projectId={projectId}
        projectName={projectName}
        handleAddTaskToProject={handleAddTaskToProject}
        hasAuthority={hasAuthority}
      />
    </div>
  );
}

export default TaskListTableForProject;
