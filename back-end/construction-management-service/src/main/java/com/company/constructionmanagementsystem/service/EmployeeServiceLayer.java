package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.HEAD;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public EmployeeViewModel findEmployeeById(int id) {
        if (!employeeRepository.findById(id).isPresent()) throw new NotFoundException("Employee Not Found.");

        Employee employee = employeeRepository.findById(id).get();

        return buildEmployeeViewModel(employee);
    }

    public EmployeeViewModel findEmployeeByEmail(String email) {

        if (!employeeRepository.findByEmail(email).isPresent()) throw new NotFoundException("Email Not Found.");

        Employee employee = employeeRepository.findByEmail(email).get();

        return buildEmployeeViewModel(employee);
    }

    public List<EmployeeViewModel> findEmployeeByName(String name) {
        if(employeeRepository.findByName(name).size() < 1) {
            throw new NotFoundException("Employee name not found.");
        }

        Employee employee = employeeRepository.findByName(name).get(0);

        List<Employee> employees = employeeRepository.findByName(name);

        List<EmployeeViewModel> employeeList = new ArrayList<>();

        for (Employee e: employees) {
            employeeList.add(buildEmployeeViewModel(e));
        }


        return employeeList;
    }

    public EmployeeViewModel findEmployeeByUsername(String username) {

        if (!employeeRepository.findByUsername(username).isPresent())
            throw new NotFoundException("Username Not Found.");
        Employee employee = employeeRepository.findByUsername(username).get();

        return buildEmployeeViewModel(employee);
    }

    public List<EmployeeViewModel> findEmployeesByTitle(String title) {
        if(employeeRepository.findByTitle(title).size() < 1) {
            throw new NotFoundException("Title Not Found.");
        }

        List<Employee> employeeList = employeeRepository.findByTitle(title.toLowerCase(Locale.ROOT));

        List<EmployeeViewModel> evmList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeViewModel evm = buildEmployeeViewModel(employee);
            evmList.add(evm);
        }
        return evmList;
    }

    public List<EmployeeViewModel> findAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeViewModel> evmList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeViewModel evm = buildEmployeeViewModel(employee);
            evmList.add(evm);
        }
        return evmList;
    }

    public List<EmployeeViewModel> findEmployeesByProjectId(Integer projectId) {


        List<Employee> employeeList = employeeRepository.findByProjectId(projectId);

        List<EmployeeViewModel> evmList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeViewModel evm = buildEmployeeViewModel(employee);
            evmList.add(evm);
        }
        return evmList;
    }

    private EmployeeViewModel buildEmployeeViewModel(Employee employee) {

        List<Task> taskList = taskRepository.findAllTasksByEmployeeId(employee.getId());
        EmployeeViewModel evm = new EmployeeViewModel();

        if (!projectRepository.findById(employee.getProjectId()).isPresent()) {
            evm.setProject(null);
        } else {
            evm.setProject(projectRepository.findById(employee.getProjectId()).get());
        }

        evm.setTaskList(taskList);
        evm.setId(employee.getId());
        evm.setEmail(employee.getEmail());
        evm.setName(employee.getName());
        evm.setSalary(employee.getSalary());
        evm.setTitle(employee.getTitle());
        evm.setDateOfBirth(employee.getDateOfBirth());
        evm.setPassword(employee.getPassword());
        evm.setPhoneNumber(employee.getPhoneNumber());
        evm.setUsername(employee.getUsername());
        evm.setUserSince(employee.getUserSince());
        evm.setYearsOfExperience(employee.getYearsOfExperience());

        return evm;
    }


    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id).get();

        // Find all relevant tasks and project
        List<Task> allRelevantTasks = taskRepository.findAllTasksByEmployeeId(id);

        if (allRelevantTasks.size() > 0) {

            for (Task task : allRelevantTasks) {
                taskRepository.deleteById(task.getId());
            }
        }
        employeeRepository.deleteById(id);
    }

    public void updateEmployeePassword(Integer id, String newPassword) {
        if (!employeeRepository.findById(id).isPresent()) throw new IllegalArgumentException("Employee not found.");

        Employee employee = employeeRepository.findById(id).get();

        employee.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));


        employeeRepository.saveAndFlush(employee);

    }
}

