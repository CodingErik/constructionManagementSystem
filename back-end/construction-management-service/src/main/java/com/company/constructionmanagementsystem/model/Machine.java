package com.company.constructionmanagementsystem.model;

import java.util.Objects;


public class Machine {

    private int id;
    private int crane;
    private int forklift;
    private int ladder;
    private int drill;

    public Machine(){}

    public Machine(int crane, int forklift, int ladder, int drill) {
        this.crane = crane;
        this.forklift = forklift;
        this.ladder = ladder;
        this.drill = drill;
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
        return crane == machine.crane && forklift == machine.forklift && ladder == machine.ladder && drill == machine.drill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(crane, forklift, ladder, drill);
    }

    @Override
    public String toString() {
        return "Machinery{" +
                "crane=" + crane +
                ", forklift=" + forklift +
                ", ladder=" + ladder +
                ", drill=" + drill +
                '}';
    }
}
