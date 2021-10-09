package com.company.constructionmanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer projectId;
    @NotEmpty(message = "The employee must have a title")
    private String title;
    @NotEmpty(message = "The employee must have a name")
    private String name;
    private LocalDate dateOfBirth; // Date of birth
    private BigDecimal salary;
    private Integer yearsOfExperience; // Years of experience
    private String email;
    private String phoneNumber;
    @NotEmpty(message = "The employee must have an username")
    private String username;
    @NotEmpty(message = "The employee must have a password")
    private String password;
    private LocalDate userSince;

    public Employee() {
    }

    public Employee(Integer id, Integer projectId, String title, String name, LocalDate dateOfBirth, BigDecimal salary, Integer yearsOfExperience, String email, String phoneNumber, String username, String password, LocalDate userSince) {


        this.id = id;
        this.projectId = projectId;
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

    public void setYearsOfExperience(Integer yearsOfExperience) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return yearsOfExperience == employee.yearsOfExperience && Objects.equals(id, employee.id) && Objects.equals(projectId, employee.projectId) && Objects.equals(title, employee.title) && Objects.equals(name, employee.name) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(salary, employee.salary) && Objects.equals(email, employee.email) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(username, employee.username) && Objects.equals(password, employee.password) && Objects.equals(userSince, employee.userSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, title, name, dateOfBirth, salary, yearsOfExperience, email, phoneNumber, username, password, userSince);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", projectId=" + projectId +
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
                '}';
    }
}
