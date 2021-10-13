package com.company.constructionmanagementsystem.viewmodel;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.ProjectMaterials;
import com.company.constructionmanagementsystem.model.Task;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectViewModel {

    private int id;
    private String name;
    private LocalDate deadline;
    private LocalDate startDate;
    private String roomType;
    private boolean isPlumbing;
    private boolean isElectric;
    private BigDecimal materialBudget;
    private BigDecimal laborBudget;
    private BigDecimal totalBudget;
    private String status;
    private ProjectMaterials materials;
    private List<Task> taskList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public ProjectViewModel() {
    }

    public ProjectViewModel(int id, String name, LocalDate deadline, LocalDate startDate, String roomType, boolean isPlumbing, boolean isElectric, BigDecimal materialBudget, BigDecimal laborBudget, BigDecimal totalBudget, String status, ProjectMaterials materials, List<Task> taskList, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.startDate = startDate;
        this.roomType = roomType;
        this.isPlumbing = isPlumbing;
        this.isElectric = isElectric;
        this.materialBudget = materialBudget;
        this.laborBudget = laborBudget;
        this.totalBudget = totalBudget;
        this.status = status;
        this.materials = materials;
        this.taskList = taskList;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isPlumbing() {
        return isPlumbing;
    }

    public void setPlumbing(boolean plumbing) {
        isPlumbing = plumbing;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public BigDecimal getMaterialBudget() {
        return materialBudget;
    }

    public void setMaterialBudget(BigDecimal materialBudget) {
        this.materialBudget = materialBudget;
    }

    public BigDecimal getLaborBudget() {
        return laborBudget;
    }

    public void setLaborBudget(BigDecimal laborBudget) {
        this.laborBudget = laborBudget;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectMaterials getMaterials() {
        return materials;
    }

    public void setMaterials(ProjectMaterials materials) {
        this.materials = materials;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
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
        ProjectViewModel that = (ProjectViewModel) o;
        return id == that.id && isPlumbing == that.isPlumbing && isElectric == that.isElectric && Objects.equals(name, that.name) && Objects.equals(deadline, that.deadline) && Objects.equals(startDate, that.startDate) && Objects.equals(roomType, that.roomType) && Objects.equals(materialBudget, that.materialBudget) && Objects.equals(laborBudget, that.laborBudget) && Objects.equals(totalBudget, that.totalBudget) && Objects.equals(status, that.status) && Objects.equals(materials, that.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deadline, startDate, roomType, isPlumbing, isElectric, materialBudget, laborBudget, totalBudget, status, materials);
    }

    @Override
    public String toString() {
        return "ProjectViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", startDate=" + startDate +
                ", roomType='" + roomType + '\'' +
                ", isPlumbing=" + isPlumbing +
                ", isElectric=" + isElectric +
                ", materialBudget=" + materialBudget +
                ", laborBudget=" + laborBudget +
                ", totalBudget=" + totalBudget +
                ", status='" + status + '\'' +
                ", materials=" + materials +
                ", taskList=" + taskList +
                ", employeeList=" + employeeList +
                '}';
    }
}
