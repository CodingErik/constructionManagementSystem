package com.company.constructionmanagementsystem.viewmodel;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskViewModel {

    private int id;
    private String name;
    private LocalDate startState;
    private LocalDate deadline;
    private String description;
    private String status;
    private List<Project> projectList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public TaskViewModel() {
    }

    public TaskViewModel(int id, String name, LocalDate startState, LocalDate deadline, String description, String status, List<Project> projectList, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.startState = startState;
        this.deadline = deadline;
        this.description = description;
        this.status = status;
        this.projectList = projectList;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartState() {
        return startState;
    }

    public void setStartState(LocalDate startState) {
        this.startState = startState;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskViewModel that = (TaskViewModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(startState, that.startState) && Objects.equals(deadline, that.deadline) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startState, deadline, description, status);
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startState=" + startState +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", projectList=" + projectList +
                ", employeeList=" + employeeList +
                '}';
    }
}
