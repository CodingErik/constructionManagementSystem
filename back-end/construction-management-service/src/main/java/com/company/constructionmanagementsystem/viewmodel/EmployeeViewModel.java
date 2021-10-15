package com.company.constructionmanagementsystem.viewmodel;

import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeViewModel {

    private int id;
    private String name;
    private String username;
    private String password;
    private String title;
    private String phoneNumber;
    private String email;
    private BigDecimal salary;
    private LocalDate dateOfBirth;
    private LocalDate userSince;
    private int yearsOfExperience;
    private Project project;
    private List<Task> taskList = new ArrayList<>();

    public EmployeeViewModel() {
    }

    public EmployeeViewModel(int id, String title, String name, LocalDate dateOfBirth, BigDecimal salary, int yearsOfExperience, String email, String phoneNumber, String username, String password, LocalDate userSince, Project project, List<Task> taskList) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.userSince = userSince;
        this.project = project;
        this.taskList = taskList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getUserSince() {
        return userSince;
    }

    public void setUserSince(LocalDate userSince) {
        this.userSince = userSince;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeViewModel that = (EmployeeViewModel) o;
        return id == that.id && yearsOfExperience == that.yearsOfExperience && Objects.equals(title, that.title) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(salary, that.salary) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userSince, that.userSince) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, name, dateOfBirth, salary, yearsOfExperience, email, phoneNumber, username, password, userSince, project);
    }

    @Override
    public String toString() {
        return "EmployeeViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", yearsOfExperience=" + yearsOfExperience +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userSince=" + userSince +
                ", project=" + project +
                ", taskList=" + taskList +
                '}';
    }
}
