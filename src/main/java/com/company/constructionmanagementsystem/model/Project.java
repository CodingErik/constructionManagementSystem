package com.company.constructionmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Your must have a name for your project")
    private String name;
    private String deadline;
    private String startDate;
    private String roomType;
    private boolean isPlumbing;
    private boolean isElectric;
    private BigDecimal materialBudget;
    private BigDecimal laborBudget;
    private BigDecimal totalBudget;
    private String status;

    public Project() {
    }

    public Project(Integer id, String name, String deadline, String startDate, String roomType, boolean isPlumbing, boolean isElectric, BigDecimal materialBudget, BigDecimal laborBudget, BigDecimal totalBudget, String status) {
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return isPlumbing == project.isPlumbing && isElectric == project.isElectric && Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(deadline, project.deadline) && Objects.equals(startDate, project.startDate) && Objects.equals(roomType, project.roomType) && Objects.equals(materialBudget, project.materialBudget) && Objects.equals(laborBudget, project.laborBudget) && Objects.equals(totalBudget, project.totalBudget) && Objects.equals(status, project.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deadline, startDate, roomType, isPlumbing, isElectric, materialBudget, laborBudget, totalBudget, status);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline='" + deadline + '\'' +
                ", startDate='" + startDate + '\'' +
                ", roomType='" + roomType + '\'' +
                ", isPlumbing=" + isPlumbing +
                ", isElectric=" + isElectric +
                ", materialBudget=" + materialBudget +
                ", laborBudget=" + laborBudget +
                ", totalBudget=" + totalBudget +
                ", status='" + status + '\'' +
                '}';
    }
}
