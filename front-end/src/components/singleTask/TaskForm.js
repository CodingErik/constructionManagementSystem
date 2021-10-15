import { useEffect, useRef, useState } from 'react';
import { EmployeeAPI, TaskAPI, ProjectAPI } from '../../api';
import { useParams } from 'react-router-dom';

function TaskForm({ task, hasAuthority, isAddTaskForm }) {
  const { taskId } = useParams();
  const [taskState, setTaskState] = useState();
  const [projectState, setProjectState] = useState();
  const [employeeState, setEmployeeState] = useState();
  const [projectList, setProjectList] = useState([]);
  const [
    availableEmployeesInProjectState,
    setAvailableEmployeesInProjectState,
  ] = useState([]);
  const nameRef = useRef(null);
  const startDateRef = useRef(null);
  const deadlineRef = useRef(null);
  const descriptionRef = useRef(null);

  useEffect(() => {
    async function fetchData() {
      const projects = await ProjectAPI.getAllProjects();
      const taskInfo = await TaskAPI.getTaskById(taskId);
      console.log(projects.data);
      setTaskState(taskInfo.data?.status);
      setProjectState(taskInfo.data.project?.id);
      setEmployeeState(taskInfo.data.employee?.id);
      setProjectList(projects.data);
    }
    fetchData();
  }, []);

  useEffect(() => {
    async function fetchData() {
      if (projectState !== null) {
        const availableEmployees = await EmployeeAPI.getAllEmployeeByProjectId(
          projectState
        );
        setAvailableEmployeesInProjectState(
          [...availableEmployees.data].filter(
            (employee) => employee.title === 'employee'
          )
        );
      }
    }
    fetchData();
  }, [projectState]);

  const handleSubmit = (event) => {
    event.preventDefault();
    let updatedTaskInformation = {};

    if (isAddTaskForm) {
      updatedTaskInformation = {
        id: null,
        name: nameRef.current.value,
        status: taskState,
        startDate: startDateRef.current.value,
        deadline: deadlineRef.current.value,
        projectId: projectState ? projectState : 0,
        employeeId: employeeState,
        description: descriptionRef.current.value,
      };
    } else {
      updatedTaskInformation = {
        id: task.id ? task.id : '',
        name: nameRef.current.value,
        status: taskState,
        startDate: startDateRef.current.value,
        deadline: deadlineRef.current.value,
        projectId: projectState ? projectState : 1,
        employeeId: employeeState,
        description: descriptionRef.current.value,
      };
    }
    EmployeeAPI.getEmployeeById(employeeState).then((response) => {
      const updatedEmployeeInformation = {
        ...response.data,
        projectId: projectState,
      };
      EmployeeAPI.putEmployee(updatedEmployeeInformation);
    });
    TaskAPI.putTask(updatedTaskInformation);
  };

  return (
    <div>
      <form style={{ margin: '0 auto' }} onSubmit={handleSubmit}>
        <fieldset>
          <div className='form-group'>
            <label
              htmlFor='taskTitle'
              className='form-label mt-4 ms-4 d-flex align-items-start'
            >
              Task Id #{task.id}
            </label>
            <input
              className='form-control ms-4'
              id='taskTitle'
              defaultValue={task.name}
              readOnly={!hasAuthority}
              ref={nameRef}
            />
          </div>
          <div className='form-group'>
            <label
              htmlFor='status'
              className='form-label mt-4 ms-4 d-flex align-items-start'
            >
              Status
            </label>
            <select
              className='form-select ms-4'
              id='status'
              disabled={!hasAuthority}
              value={taskState}
              onChange={(event) => setTaskState(event.target.value)}
            >
              <option value='in_progress'>In Progress</option>
              <option value='completed'>Completed</option>
              <option value='cancelled'>Cancelled</option>
            </select>
          </div>
          <div className='form-group'>
            <div className='form-group row'>
              <label htmlFor='startDate' className='col-sm-2 col-form-label'>
                Start Date
              </label>
              <div className='col-sm-4'>
                <input
                  type='date'
                  readOnly={!hasAuthority}
                  className='form-control-plaintext'
                  id='startDate'
                  defaultValue={task.startDate}
                  ref={startDateRef}
                />
              </div>
              <label htmlFor='deadline' className='col-sm-2 col-form-label'>
                Deadline
              </label>
              <div className='col-sm-4'>
                <input
                  type='date'
                  readOnly={!hasAuthority}
                  className='form-control-plaintext'
                  id='deadline'
                  defaultValue={task.deadline}
                  ref={deadlineRef}
                />
              </div>
            </div>
          </div>
          <div className='form-group'>
            <label
              htmlFor='project'
              className='form-label mt-4 ms-4 d-flex align-items-start'
            >
              Project Containing This Task
            </label>
            <select
              className='form-select ms-4'
              id='project'
              disabled={isAddTaskForm ? false : true}
              value={projectState}
              onChange={(event) => setProjectState(event.target.value)}
            >
              {projectList.map((project) => (
                <option value={project.id} key={project.id}>
                  {project.id}. {project.name}
                </option>
              ))}
            </select>
          </div>
          <div className='form-group'>
            <label
              htmlFor='employee'
              className='form-label mt-4 ms-4 d-flex align-items-start'
            >
              Designate Employee For This Task
            </label>
            <select
              className='form-select ms-4'
              id='employee'
              disabled={!hasAuthority}
              value={employeeState}
              onChange={(event) => setEmployeeState(event.target.value)}
            >
              <option value={0}>0. None</option>
              {availableEmployeesInProjectState.map((employee) => (
                <option value={employee.id} key={employee.id}>
                  {employee.id}. {employee.name}
                </option>
              ))}
            </select>
          </div>
          <div className='form-group'>
            <label
              htmlFor='description'
              className='form-label mt-4 ms-4 d-flex align-items-start'
            >
              Description
            </label>
            <textarea
              defaultValue={task.description}
              disabled={!hasAuthority}
              className='form-control ms-4'
              id='description'
              rows='8'
              ref={descriptionRef}
            ></textarea>
          </div>
        </fieldset>
      </form>
    </div>
  );
}

export default TaskForm;
