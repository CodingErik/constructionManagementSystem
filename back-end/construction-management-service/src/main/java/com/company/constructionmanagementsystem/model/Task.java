package com.company.constructionmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "You must supply a name")
    private String name;
    @NotNull(message = "You must supply a project Id")
    private Integer projectId;
    private Integer employeeId;
    private LocalDate startDate;
    private LocalDate deadline;
    private String description;
    private String status;

    public Task() {
    }

    public Task(Integer id, Integer projectId, Integer employeeId, String name, LocalDate startDate, LocalDate deadline, String description, String status) {
        this.id = id;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.name = name;
        this.startDate = startDate;
        this.deadline = deadline;
        this.description = description;
        this.status = status;
    }

    public Task(String name, Integer projectId, Integer employeeId, LocalDate startDate, LocalDate deadline, String description, String status) {
        this.name = name;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.deadline = deadline;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(projectId, task.projectId) && Objects.equals(employeeId, task.employeeId) && Objects.equals(name, task.name) && Objects.equals(startDate, task.startDate) && Objects.equals(deadline, task.deadline) && Objects.equals(description, task.description) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, employeeId, name, startDate, deadline, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", startState=" + startDate +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
