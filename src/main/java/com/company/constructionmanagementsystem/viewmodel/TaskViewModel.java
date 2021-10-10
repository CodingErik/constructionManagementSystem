package com.company.constructionmanagementsystem.viewmodel;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskViewModel {

    private int id;
    private Project project;
    private Employee employee;
    private String name;
    private LocalDate startDate;
    private LocalDate deadline;
    private String description;
    private String status;

    public TaskViewModel() {
    }

    public TaskViewModel(int id, Project project, Employee employee, String name, LocalDate startDate, LocalDate deadline, String description, String status) {
        this.id = id;
        this.project = project;
        this.employee = employee;
        this.name = name;
        this.startDate = startDate;
        this.deadline = deadline;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskViewModel that = (TaskViewModel) o;
        return id == that.id && Objects.equals(project, that.project) && Objects.equals(employee, that.employee) && Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate) && Objects.equals(deadline, that.deadline) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, employee, name, startDate, deadline, description, status);
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "id=" + id +
                ", project=" + project +
                ", employee=" + employee +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
