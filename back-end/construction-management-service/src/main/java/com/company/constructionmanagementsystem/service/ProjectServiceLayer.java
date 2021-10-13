package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class ProjectServiceLayer {

    ProjectRepository projectRepository;
    EmployeeRepository employeeRepository;
    TaskRepository taskRepository;

    @Autowired
    public ProjectServiceLayer(ProjectRepository projectRepository, EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    public ProjectViewModel findById(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        Project project = null;

        if (optionalProject.isPresent()) {
            project = optionalProject.get();
        } else {
            return null;
        }

        return buildProjectViewModel(project);

    }

    public List<ProjectViewModel> findAllProjects() {
        List<Project> findAll = projectRepository.findAll();

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for (Project project : findAll) {
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }

        return pvmList;
    }

    public List<ProjectViewModel> findByStatus(String status) {
        List<Project> byStatus = projectRepository.findByStatus(status);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for (Project project: byStatus) {
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);

        }

        return pvmList;
    }

    public List<ProjectViewModel> findByDeadline(LocalDate deadline) {
        List<Project> byDeadline = projectRepository.findByDeadline(deadline);


        List<ProjectViewModel> pvmList = new ArrayList<>();

        for (Project project: byDeadline) {
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }

        return pvmList;

    }

    public List<ProjectViewModel> findByStartDate(LocalDate startDate) {
        List<Project> byDeadline = projectRepository.findByStartDate(startDate);


        List<ProjectViewModel> pvmList = new ArrayList<>();

        for (Project project: byDeadline) {
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }

        return pvmList;

    }

    public List<ProjectViewModel> findByRoomType(String roomType){
        List<Project> projectList = projectRepository.findByRoomType(roomType);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }
        return pvmList;
    }

    public List<ProjectViewModel> findByName(String name){
        List<Project> projectList = projectRepository.findByName(name);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }
        return pvmList;
    }

    public List<ProjectViewModel> findByRoomTypeAndName(String roomType, String name){
        List<Project> projectList = projectRepository.findByRoomTypeAndName(roomType,name);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            ProjectViewModel pvm = buildProjectViewModel(project);

            pvmList.add(pvm);
        }
        return pvmList;
    }

    public ProjectViewModel buildProjectViewModel(Project inputProject) {


        List<Task> taskList = taskRepository.findAllTasksByProjectId(inputProject.getId());

        List<Employee> employeeList = employeeRepository.findByProjectId(inputProject.getId());

        ProjectViewModel pvm = new ProjectViewModel();

        pvm.setId(inputProject.getId());
        pvm.setName(inputProject.getName());
        pvm.setStartDate(inputProject.getStartDate());
        pvm.setDeadline(inputProject.getDeadline());
        pvm.setRoomType(inputProject.getRoomType());
        pvm.setPlumbing(inputProject.isPlumbing());
        pvm.setElectric(inputProject.isElectric());
        pvm.setMaterialBudget(inputProject.getMaterialBudget());
        pvm.setLaborBudget(inputProject.getLaborBudget());
        pvm.setTotalBudget(inputProject.getTotalBudget());
        pvm.setStatus(inputProject.getStatus());

        pvm.setEmployeeList(employeeList);
        pvm.setTaskList(taskList);

        return pvm;

    }
}
