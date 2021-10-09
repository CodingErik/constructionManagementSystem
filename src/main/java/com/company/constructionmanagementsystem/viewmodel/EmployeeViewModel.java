package com.company.constructionmanagementsystem.viewmodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EmployeeViewModel {

    private int id;
    private String title;
    private String name;
    private LocalDate dob;
    private BigDecimal salary;
    private int yearsOfExperience;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private LocalDate userSince;
    private List<Project> projectList = new ArrayList<>();

    public EmployeeViewModel() {
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeViewModel that = (EmployeeViewModel) o;
        return id == that.id && yearsOfExperience == that.yearsOfExperience && Objects.equals(title, that.title) && Objects.equals(name, that.name) && Objects.equals(dob, that.dob) && Objects.equals(salary, that.salary) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userSince, that.userSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, name, dob, salary, yearsOfExperience, email, phoneNumber, username, password, userSince);
    }

    @Override
    public String toString() {
        return "EmployeeViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", yearsOfExperience=" + yearsOfExperience +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userSince=" + userSince +
                ", projectList=" + projectList +
                '}';
    }
}
