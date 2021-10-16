package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "machinery")
public class Machinery {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private int crane;
    private int forklift;
    private int ladder;
    private int drill;

    public Machinery(){}

    public Machinery(int crane, int forklift, int ladder, int drill) {
        this.crane = crane;
        this.forklift = forklift;
        this.ladder = ladder;
        this.drill = drill;
    }

    public Machinery(int id, int crane, int forklift, int ladder, int drill) {
        this.id = id;
        this.crane = crane;
        this.forklift = forklift;
        this.ladder = ladder;
        this.drill = drill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Machinery machinery = (Machinery) o;
        return id == machinery.id && crane == machinery.crane && forklift == machinery.forklift && ladder == machinery.ladder && drill == machinery.drill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, crane, forklift, ladder, drill);
    }

    @Override
    public String toString() {
        return "Machinery{" +
                "id=" + id +
                ", crane=" + crane +
                ", forklift=" + forklift +
                ", ladder=" + ladder +
                ", drill=" + drill +
                '}';
    }
}
