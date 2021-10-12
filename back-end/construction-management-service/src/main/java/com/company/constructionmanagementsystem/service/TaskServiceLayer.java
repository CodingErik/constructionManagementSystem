package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.viewmodel.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TaskServiceLayer {
    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public TaskServiceLayer(TaskRepository taskRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskViewModel> findAllTasks(){
        List<Task> taskList = taskRepository.findAll();
        List<TaskViewModel> tvmList = new ArrayList<>();
        for(Task task : taskList){
            TaskViewModel tvm = buildTaskViewModel(task);
            tvmList.add(tvm);
        }
        return tvmList;
    }

    public TaskViewModel findTaskById(Integer id) {
        Task task = taskRepository.getById(id);
        return buildTaskViewModel(task);
    }

    // ******************************************************
    // ******************************************************
    // ******************************************************
    // ******************************************************
    // ******************************************************

    public List<TaskViewModel> findTasksByName(String name){
        List<Task> taskList = taskRepository.findAllTasksByName(name);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }

    public List<TaskViewModel> findAllTasksByProjectId(Integer projectId){
        List<Task> taskList = taskRepository.findAllTasksByProjectId(projectId);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }


    public List<TaskViewModel> findAllTasksByEmployeeId(Integer employeeId) {
        List<Task> taskList = taskRepository.findAllTasksByEmployeeId(employeeId);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }

    public List<TaskViewModel> findAllTasksByProjectIdAndEmployeeId(Integer projectId, Integer employeeId){
        List<Task> taskList = taskRepository.findAllTasksByProjectIdAndEmployeeId(projectId, employeeId);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }


    public List<TaskViewModel> findAllTasksByEmployeeIdAndName(Integer employeeId, String name){
        List<Task> taskList = taskRepository.findAllTasksByEmployeeIdAndName(employeeId, name);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }

    public List<TaskViewModel> findAllTasksByProjectIdAndName(Integer projectId, String name){
        List<Task> taskList = taskRepository.findAllTasksByProjectIdAndName(projectId, name);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }



    public List<TaskViewModel> findAllTasksByProjectIdAndEmployeeIdAndName(Integer projectId,Integer employeeId, String name){
        List<Task> taskList = taskRepository.findAllTasksByProjectIdAndEmployeeIdAndName(projectId,employeeId,name);
        List<TaskViewModel> returnTaskViewModelList = new ArrayList<>();
        taskList.stream()
                .forEach(task -> {
                    returnTaskViewModelList.add(buildTaskViewModel(task));
                });
        return returnTaskViewModelList;
    }

    private TaskViewModel buildTaskViewModel(Task task) {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setId(task.getId());
        taskViewModel.setName(task.getName());
        taskViewModel.setStartDate(task.getStartDate());
        taskViewModel.setDeadline(task.getDeadline());
        taskViewModel.setDescription(task.getDescription());
        taskViewModel.setStatus(task.getStatus());
        taskViewModel.setProject(projectRepository.getById(task.getProjectId()));
        taskViewModel.setEmployee(employeeRepository.getById(task.getEmployeeId()));

        return taskViewModel;
    }

}
