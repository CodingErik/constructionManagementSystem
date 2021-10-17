package com.company.constructionmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer projectId;
    private int crane;
    private int forklift;
    private int ladder;
    private int drill;

    public Machine(){}

    public Machine(Integer id, Integer projectId, int crane, int forklift, int ladder, int drill) {
        this.id = id;
        this.projectId = projectId;
        this.crane = crane;
        this.forklift = forklift;
        this.ladder = ladder;
        this.drill = drill;
    }

    public Machine(Integer projectId, int crane, int forklift, int ladder, int drill) {
        this.projectId = projectId;
        this.crane = crane;
        this.forklift = forklift;
        this.ladder = ladder;
        this.drill = drill;
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

    public int getCrane() {
        return crane;
    }

    public void setCrane(int crane) {
        this.crane = crane;
    }

    public int getForklift() {
        return forklift;
    }

    public void setForklift(int forklift) {
        this.forklift = forklift;
    }

    public int getLadder() {
        return ladder;
    }

    public void setLadder(int ladder) {
        this.ladder = ladder;
    }

    public int getDrill() {
        return drill;
    }

    public void setDrill(int drill) {
        this.drill = drill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return crane == machine.crane && forklift == machine.forklift && ladder == machine.ladder && drill == machine.drill && Objects.equals(id, machine.id) && Objects.equals(projectId, machine.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, crane, forklift, ladder, drill);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", crane=" + crane +
                ", forklift=" + forklift +
                ", ladder=" + ladder +
                ", drill=" + drill +
                '}';
    }
}
