package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class EmployeeServiceLayer {

    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    @Autowired
    public EmployeeServiceLayer(EmployeeRepository employeeRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public EmployeeViewModel findEmployeeById(int id){
        Employee employee = employeeRepository.findById(id).get();

        return buildEmployeeViewModel(employee);
    }

    public EmployeeViewModel findEmployeeByEmail(String email){
        Employee employee = employeeRepository.findByEmail(email).get();

        return buildEmployeeViewModel(employee);
    }

    public EmployeeViewModel findEmployeeByName(String name){
        Employee employee = employeeRepository.findByName(name).get();

        return buildEmployeeViewModel(employee);
    }

    public EmployeeViewModel findEmployeeByUsername(String username){
        Employee employee = employeeRepository.findByUsername(username).get();

        return buildEmployeeViewModel(employee);
    }

    public List<EmployeeViewModel> findEmployeesByTitle(String title){
        List<Employee> employeeList = employeeRepository.findByTitle(title);

        List<EmployeeViewModel> evmList = new ArrayList<>();

        for(Employee employee : employeeList){
            EmployeeViewModel evm = buildEmployeeViewModel(employee);
            evmList.add(evm);
        }
        return evmList;
    }

    public List<EmployeeViewModel> findEmployeesByProjectId(Integer projectId){
        List<Employee> employeeList = employeeRepository.findByProjectId(projectId);

        List<EmployeeViewModel> evmList = new ArrayList<>();

        for(Employee employee : employeeList){
            EmployeeViewModel evm = buildEmployeeViewModel(employee);
            evmList.add(evm);
        }
        return evmList;
    }

    private EmployeeViewModel buildEmployeeViewModel(Employee employee){
        Project project = projectRepository.findById(employee.getProjectId()).get();
        List<Task> taskList = taskRepository.findAllTasksByEmployeeId(employee.getId());

        EmployeeViewModel evm = new EmployeeViewModel();
        evm.setProject(project);
        evm.setTaskList(taskList);
        evm.setId(employee.getId());
        evm.setEmail(employee.getEmail());
        evm.setName(employee.getName());
        evm.setSalary(employee.getSalary());
        evm.setTitle(employee.getTitle());
        evm.setDob(employee.getDateOfBirth());
        evm.setPassword(employee.getPassword());
        evm.setPhoneNumber(employee.getPhoneNumber());
        evm.setUsername(employee.getUsername());
        evm.setUserSince(employee.getUserSince());
        evm.setYearsOfExperience(employee.getYearsOfExperience());

        return evm;
    }
}

