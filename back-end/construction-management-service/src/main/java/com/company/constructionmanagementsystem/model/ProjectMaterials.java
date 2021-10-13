package com.company.constructionmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "projectMaterials")
public class ProjectMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer projectId;
    private long steel;
    private long brick;
    private long lumber;
    private long cement;

    public ProjectMaterials(){};

    public ProjectMaterials(Integer id, Integer projectId, long steel, long brick, long lumber, long cement) {
        this.id = id;
        this.projectId = projectId;
        this.steel = steel;
        this.brick = brick;
        this.lumber = lumber;
        this.cement = cement;
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

    public long getSteel() {
        return steel;
    }

    public void setSteel(long steel) {
        this.steel = steel;
    }

    public long getBrick() {
        return brick;
    }

    public void setBrick(long brick) {
        this.brick = brick;
    }

    public long getLumber() {
        return lumber;
    }

    public void setLumber(long lumber) {
        this.lumber = lumber;
    }

    public long getCement() {
        return cement;
    }

    public void setCement(long cement) {
        this.cement = cement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMaterials that = (ProjectMaterials) o;
        return steel == that.steel && brick == that.brick && lumber == that.lumber && cement == that.cement && Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, steel, brick, lumber, cement);
    }

    @Override
    public String toString() {
        return "ProjectMaterials{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", steel=" + steel +
                ", brick=" + brick +
                ", lumber=" + lumber +
                ", cement=" + cement +
                '}';
    }
}
