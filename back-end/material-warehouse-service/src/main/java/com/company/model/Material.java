package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "material")
public class Material {

    @Id
    private int id;
    private long steel;
    private long brick;
    private long lumber;
    private long cement;

    public Material(){}

    public Material(int id, long steel, long brick, long lumber, long cement) {
        this.id = id;
        this.steel = steel;
        this.brick = brick;
        this.lumber = lumber;
        this.cement = cement;
    }

    public Material(long steel, long brick, long lumber, long cement) {
        this.steel = steel;
        this.brick = brick;
        this.lumber = lumber;
        this.cement = cement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Material material = (Material) o;
        return id == material.id && steel == material.steel && brick == material.brick && lumber == material.lumber && cement == material.cement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, steel, brick, lumber, cement);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", steel=" + steel +
                ", brick=" + brick +
                ", lumber=" + lumber +
                ", cement=" + cement +
                '}';
    }
}
