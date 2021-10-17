package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.*;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.service.ProjectServiceLayer;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
//@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ProjectControllerTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    MachineRepository machineRepository;

    @MockBean
    MaterialRepository materialRepository;

    @MockBean
    ProjectServiceLayer projectServiceLayer;

    @MockBean
    FeignContext feignContext;

    @MockBean
    FeignLoggerFactory feignLoggerFactory;

    @MockBean
    MachineWarehouseClient machineWarehouseClient;

    @MockBean
    MaterialWarehouseClient materialWarehouseClient;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    List<Employee> employeeInputList;

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
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        java.time.LocalDate deadline = java.time.LocalDate.of(2010,4 ,4);
        java.time.LocalDate startDate = java.time.LocalDate.of(1999,1 ,1);
        MathContext mathContext = new MathContext(4);

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        employee1 = new Employee(1,1,"Architect","Amal",birth,new BigDecimal(430.33).round(mathContext),4,"amalj2426@gmail.com","314-378-4297","amalj",null,since);
        employee2 = new Employee(2,1,"Worker","Hannah",birth,new BigDecimal(430.33).round(mathContext),2,"hannahb@gmail.com","422-987-2243","hannahb",null,since);
        employee3 = new Employee(3,2,"Architect","Nargiza",birth,new BigDecimal(430.33).round(mathContext),8,"nargiza@gmail.com","224-493-4288","narg",null,since);
        employee4 = new Employee(4,2,"Worker","Milana",birth,new BigDecimal(430.33).round(mathContext),1,"milana@gmail.com","734-902-2987","milan",null,since);
        employee5 = new Employee(5,2,"Worker","Tamila",birth,new BigDecimal(430.33).round(mathContext),1,"tamila@gmail.com","417-487-4227","tamil",null,since);
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
    @WithMockUser(roles = {"admin"})
    public void shouldCreateNewProject() throws Exception {
        java.time.LocalDate deadline = java.time.LocalDate.of(2010,4 ,4);
        java.time.LocalDate startDate = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Project inputProject = new Project();

        inputProject.setName("Project1");
        inputProject.setDeadline(deadline);
        inputProject.setStartDate(startDate);
        inputProject.setRoomType("Kitchen");
        inputProject.setPlumbing(true);
        inputProject.setElectric(true);
        inputProject.setMaterialBudget(new BigDecimal(9800.34).round(mathContext));
        inputProject.setLaborBudget(new BigDecimal(3480.16).round(mathContext));
        inputProject.setTotalBudget(new BigDecimal(13280.50).round(mathContext));
        inputProject.setStatus("in_progress");

        String inputJson = mapper.writeValueAsString(inputProject);

        Project outputProject = new Project(1,"Project1",deadline,startDate,"Kitchen",true,true,new BigDecimal(9800.34).round(mathContext),new BigDecimal(3480.16).round(mathContext),new BigDecimal(13280.50).round(mathContext),"in_progress");

        String outputJson = mapper.writeValueAsString(outputProject);

        given(projectRepository.save(inputProject)).willReturn(outputProject);

        mockMvc.perform(
                post("/api/projects")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnAllProjects() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            ProjectViewModel pvm = buildProjectViewModel(project);
            pvmList.add(pvm);
        }

        String outputJson = mapper.writeValueAsString(pvmList);

        given(projectServiceLayer.findAllProjects()).willReturn(pvmList);

        mockMvc.perform(get("/api/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnProjectWithCorrectId() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        ProjectViewModel targetProject = buildProjectViewModel(project1);

        String outputJson = mapper.writeValueAsString(targetProject);

        given(projectServiceLayer.findById(targetProject.getId())).willReturn(targetProject);

        mockMvc.perform(get("/api/projects/id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnListOfProjectsWithCorrectStatus() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            if(project.getStatus() == "in_progress"){
                ProjectViewModel pvm = buildProjectViewModel(project);
                pvmList.add(pvm);
            }
        }

        String outputJson = mapper.writeValueAsString(pvmList);

        given(projectServiceLayer.findByStatus("in_progress")).willReturn(pvmList);

        mockMvc.perform(get("/api/projects/status/in_progress"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnListOfProjectsWithCorrectRoomType() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            if(project.getRoomType() == "Living Room"){
                ProjectViewModel pvm = buildProjectViewModel(project);
                pvmList.add(pvm);
            }
        }

        String outputJson = mapper.writeValueAsString(pvmList);

        given(projectServiceLayer.findByRoomType("Living Room")).willReturn(pvmList);

        mockMvc.perform(get("/api/projects/roomType/Living Room"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnListOfProjectsWithCorrectName() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            if(project.getName() == "Project1"){
                ProjectViewModel pvm = buildProjectViewModel(project);
                pvmList.add(pvm);
            }
        }

        String outputJson = mapper.writeValueAsString(pvmList);

        given(projectServiceLayer.findByName("Project1")).willReturn(pvmList);

        mockMvc.perform(get("/api/projects/name/Project1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnListOfProjectsWithCorrectNameAndRoomType() throws Exception {
        projectRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<ProjectViewModel> pvmList = new ArrayList<>();

        for(Project project : projectList){
            if(project.getName() == "Project1" && project.getRoomType() == "Kitchen"){
                ProjectViewModel pvm = buildProjectViewModel(project);
                pvmList.add(pvm);
            }
        }

        String outputJson = mapper.writeValueAsString(pvmList);

        given(projectServiceLayer.findByRoomTypeAndName("Kitchen", "Project1")).willReturn(pvmList);

        mockMvc.perform(get("/api/projects/roomType/Kitchen/name/Project1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldDeleteProject() throws Exception {
        mockMvc.perform(delete("/api/projects/1")).andDo(print()).andExpect(status().isNoContent());
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