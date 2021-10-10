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

    public TaskViewModel findTaskById(Integer id) {
        Task task = taskRepository.getById(id);
        return buildTaskViewModel(task);
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
