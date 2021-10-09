package com.company.constructionmanagementsystem.model;


import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private Integer id;
    private Integer projectId;
    @NotEmpty(message = "The employee must have a title")
    private String title;
    @NotEmpty(message = "The employee must have a name")
    private String name;
    private LocalDate dob; // Date of birth
    private BigDecimal salary;
    private int yoe; // Years of experience
    private String email;
    private String phoneNumber;
    @NotEmpty(message = "The employee must have an username")
    private String username;
    @NotEmpty(message = "The employee must have a password")
    private String password;
    private LocalDate userSince;

    public Employee(Integer id, Integer projectId, String title, String name, LocalDate dob, BigDecimal salary, int yoe, String email, String phoneNumber, String username, String password, LocalDate userSince) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.yoe = yoe;
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

    public int getYoe() {
        return yoe;
    }

    public void setYoe(int yoe) {
        this.yoe = yoe;
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
        return yoe == employee.yoe && Objects.equals(id, employee.id) && Objects.equals(projectId, employee.projectId) && Objects.equals(title, employee.title) && Objects.equals(name, employee.name) && Objects.equals(dob, employee.dob) && Objects.equals(salary, employee.salary) && Objects.equals(email, employee.email) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(username, employee.username) && Objects.equals(password, employee.password) && Objects.equals(userSince, employee.userSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, title, name, dob, salary, yoe, email, phoneNumber, username, password, userSince);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", yoe=" + yoe +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userSince=" + userSince +
                '}';
    }
}
