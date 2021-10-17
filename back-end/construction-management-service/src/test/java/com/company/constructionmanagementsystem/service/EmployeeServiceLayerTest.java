package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceLayerTest {

    @InjectMocks
    EmployeeServiceLayer employeeServiceLayer;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    TaskRepository taskRepository;


    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    List<Employee> employeeInputList;

    EmployeeViewModel employeeViewModel1;
    EmployeeViewModel employeeViewModel2;
    EmployeeViewModel employeeViewModel3;
    EmployeeViewModel employeeViewModel4;
    EmployeeViewModel employeeViewModel5;
    List<EmployeeViewModel> employeeViewModelList;

    Project project1;
    Project project2;
    List<Project> projectList;

    Task task1;
    Task task2;
    Task task3;
    Task task4;
    Task task5;
    List<Task> taskList;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        java.time.LocalDate deadline = java.time.LocalDate.of(2010,4 ,4);
        java.time.LocalDate startDate = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        employee1 = new Employee(1,1,"architect","Amal",birth,new BigDecimal(430.33).round(mathContext),4,"amalj2426@gmail.com","314-378-4297","amalj",null,since);
        employee2 = new Employee(2,1,"worker","Hannah",birth,new BigDecimal(430.33).round(mathContext),2,"hannahb@gmail.com","422-987-2243","hannahb",null,since);
        employee3 = new Employee(3,2,"architect","Nargiza",birth,new BigDecimal(430.33).round(mathContext),8,"nargiza@gmail.com","224-493-4288","narg",null,since);
        employee4 = new Employee(4,2,"worker","Milana",birth,new BigDecimal(430.33).round(mathContext),1,"milana@gmail.com","734-902-2987","milan",null,since);
        employee5 = new Employee(5,2,"worker","Tamila",birth,new BigDecimal(430.33).round(mathContext),1,"tamila@gmail.com","417-487-4227","tamil",null,since);
        employeeInputList = new ArrayList<>();
        employeeInputList.add(employee1);
        employeeInputList.add(employee2);
        employeeInputList.add(employee3);
        employeeInputList.add(employee4);
        employeeInputList.add(employee5);

        project1 = new Project(1,"Project1",deadline,startDate,"Kitchen",true,true,new BigDecimal(9800.34).round(mathContext),new BigDecimal(3480.16).round(mathContext),new BigDecimal(13280.50).round(mathContext),"in_progress");
        project2 = new Project(2,"Project2",deadline,startDate,"Living Room",false,true,new BigDecimal(9000.50).round(mathContext),new BigDecimal(9000.50).round(mathContext),new BigDecimal(18001.00).round(mathContext),"completed");
        projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);

        task1 = new Task(1,1,3,"Install Windows",startDate,deadline,"Install windows","in_progress");
        task2 = new Task(2,1,2,"Remove old panel floors",startDate,deadline,"Remove floors","completed");
        task3 = new Task(3,2,1,"Paint walls",startDate,deadline,"Paint walls with grey color","in_progress");
        task4 = new Task(4,2,4,"Dispose of garbage",startDate,deadline,"Dispose of construction garbage","in_progress");
        task5 = new Task(5,2,5,"Install shelves",startDate,deadline,"Install shelves in the main part of the room","completed");
        taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);

    }

    @Test
    public void shouldReturnAllEmployees() {

        when(employeeRepository.findAll()).thenReturn(employeeInputList);

        List<EmployeeViewModel> evmList = employeeServiceLayer.findAllEmployees();

        assertEquals(5, evmList.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnEmployeeWithCorrectId() {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        List<Task> thisEmployeeTaskList = new ArrayList<>();

        for(Task task : taskList){
            if(task.getEmployeeId() == 1){
                thisEmployeeTaskList.add(task);
            }
        }

        when(employeeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(employee1));
        when(projectRepository.findById(employee1.getProjectId())).thenReturn(java.util.Optional.ofNullable(project1));
        when(taskRepository.findAllTasksByEmployeeId(1)).thenReturn(thisEmployeeTaskList);

        EmployeeViewModel evm = employeeServiceLayer.findEmployeeById(1);

        assertEquals("architect", evm.getTitle());
        assertEquals("Amal", evm.getName());
        assertEquals(birth, evm.getDateOfBirth());
        assertEquals("amalj2426@gmail.com", evm.getEmail());
        assertEquals("314-378-4297", evm.getPhoneNumber());
        assertEquals(since, evm.getUserSince());
        assertEquals(4, evm.getYearsOfExperience());
        assertEquals(project1, evm.getProject());
        assertEquals(thisEmployeeTaskList, evm.getTaskList());
    }

    @Test
    public void shouldReturnEmployeeWithCorrectEmail() {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        List<Task> thisEmployeeTaskList = new ArrayList<>();

        for(Task task : taskList){
            if(task.getEmployeeId() == 1){
                thisEmployeeTaskList.add(task);
            }
        }

        when(employeeRepository.findByEmail("amalj2426@gmail.com")).thenReturn(java.util.Optional.ofNullable(employee1));
        when(projectRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(project1));
        when(taskRepository.findAllTasksByEmployeeId(1)).thenReturn(thisEmployeeTaskList);

        EmployeeViewModel evm = employeeServiceLayer.findEmployeeByEmail("amalj2426@gmail.com");

        assertEquals("architect", evm.getTitle());
        assertEquals("Amal", evm.getName());
        assertEquals(birth, evm.getDateOfBirth());
        assertEquals("amalj2426@gmail.com", evm.getEmail());
        assertEquals("314-378-4297", evm.getPhoneNumber());
        assertEquals(since, evm.getUserSince());
        assertEquals(4, evm.getYearsOfExperience());
        assertEquals(project1, evm.getProject());
        assertEquals(thisEmployeeTaskList, evm.getTaskList());
    }

    @Test
    public void shouldReturnEmployeeWithCorrectName() {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        List<Task> thisEmployeeTaskList = new ArrayList<>();

        for(Task task : taskList){
            if(task.getEmployeeId() == 1){
                thisEmployeeTaskList.add(task);
            }
        }

        when(employeeRepository.findByName("Amal")).thenReturn(Collections.singletonList(employee1));
        when(projectRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(project1));
        when(taskRepository.findAllTasksByEmployeeId(1)).thenReturn(thisEmployeeTaskList);

        List<EmployeeViewModel> evmList = employeeServiceLayer.findEmployeeByName("Amal");

        assertEquals("architect", evmList.get(0).getTitle());
        assertEquals("Amal", evmList.get(0).getName());
        assertEquals(birth, evmList.get(0).getDateOfBirth());
        assertEquals("amalj2426@gmail.com", evmList.get(0).getEmail());
        assertEquals("314-378-4297", evmList.get(0).getPhoneNumber());
        assertEquals(since, evmList.get(0).getUserSince());
        assertEquals(4, evmList.get(0).getYearsOfExperience());
        assertEquals(project1, evmList.get(0).getProject());
        assertEquals(thisEmployeeTaskList, evmList.get(0).getTaskList());
    }

    @Test
    public void shouldReturnEmployeeWirthCorrectUsername() {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        List<Task> thisEmployeeTaskList = new ArrayList<>();

        for(Task task : taskList){
            if(task.getEmployeeId() == 1){
                thisEmployeeTaskList.add(task);
            }
        }

        when(employeeRepository.findByUsername("amalj")).thenReturn(java.util.Optional.ofNullable(employee1));
        when(projectRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(project1));
        when(taskRepository.findAllTasksByEmployeeId(1)).thenReturn(thisEmployeeTaskList);

        EmployeeViewModel evm = employeeServiceLayer.findEmployeeByUsername("amalj");

        assertEquals("architect", evm.getTitle());
        assertEquals("Amal", evm.getName());
        assertEquals(birth, evm.getDateOfBirth());
        assertEquals("amalj2426@gmail.com", evm.getEmail());
        assertEquals("314-378-4297", evm.getPhoneNumber());
        assertEquals(since, evm.getUserSince());
        assertEquals(4, evm.getYearsOfExperience());
        assertEquals(project1, evm.getProject());
        assertEquals(thisEmployeeTaskList, evm.getTaskList());
    }

    @Test
    public void shouldReturnEmployeeListWithCorrectTitle() {

        List<Employee> empListArchitect = new ArrayList<>();
        List<Employee> empListWorker = new ArrayList<>();

        for(Employee employee : employeeInputList){
            if(employee.getTitle().toLowerCase(Locale.ROOT) == "architect"){
                empListArchitect.add(employee);
            }
            if(employee.getTitle().toLowerCase(Locale.ROOT) == "worker"){
                empListWorker.add(employee);
            }
        }

        when(employeeRepository.findByTitle("architect")).thenReturn(empListArchitect);
        when(employeeRepository.findByTitle("worker")).thenReturn(empListWorker);

        List<EmployeeViewModel> evmListArchitect = employeeServiceLayer.findEmployeesByTitle("architect");
        List<EmployeeViewModel> evmListWorker = employeeServiceLayer.findEmployeesByTitle("worker");

        assertEquals(2, evmListArchitect.size());
        assertEquals(3,evmListWorker.size());
        verify(employeeRepository, times(2)).findByTitle("architect");
        verify(employeeRepository, times(2)).findByTitle("worker");
    }

    @Test
    public void shouldReturnEmployeeListWithCorrectProjectId() {
        List<Employee> empListProject1 = new ArrayList<>();
        List<Employee> empListProject2 = new ArrayList<>();

        for(Employee employee : employeeInputList){
            if(employee.getProjectId() == 1){
                empListProject1.add(employee);
            }
            if(employee.getProjectId() == 2){
                empListProject2.add(employee);
            }
        }

        when(employeeRepository.findByProjectId(1)).thenReturn(empListProject1);
        when(employeeRepository.findByProjectId(2)).thenReturn(empListProject2);

        List<EmployeeViewModel> evmListProject1 = employeeServiceLayer.findEmployeesByProjectId(1);
        List<EmployeeViewModel> evmListProject2 = employeeServiceLayer.findEmployeesByProjectId(2);

        assertEquals(2, evmListProject1.size());
        assertEquals(3, evmListProject2.size());
        verify(employeeRepository, times(1)).findByProjectId(1);
        verify(employeeRepository, times(1)).findByProjectId(2);
    }


    //        private EmployeeViewModel buildEmployeeViewModel(Employee employee){
//
//        List<Task> taskList = taskRepository.findAllTasksByEmployeeId(employee.getId());
//        EmployeeViewModel evm = new EmployeeViewModel();
//
//        if (!projectRepository.findById(employee.getProjectId()).isPresent()) {
//            evm.setProject(null);
//        } else {
//            evm.setProject(projectRepository.findById(employee.getProjectId()).get());
//        }
//
//        evm.setTaskList(taskList);
//        evm.setId(employee.getId());
//        evm.setEmail(employee.getEmail());
//        evm.setName(employee.getName());
//        evm.setSalary(employee.getSalary());
//        evm.setTitle(employee.getTitle());
//        evm.setDateOfBirth(employee.getDateOfBirth());
//        evm.setPassword(employee.getPassword());
//        evm.setPhoneNumber(employee.getPhoneNumber());
//        evm.setUsername(employee.getUsername());
//        evm.setUserSince(employee.getUserSince());
//        evm.setYearsOfExperience(employee.getYearsOfExperience());
//
//        return evm;
//    }

}