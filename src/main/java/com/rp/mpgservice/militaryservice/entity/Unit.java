package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Table(name = "unit")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Unit {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Action> actions = new ArrayList<>();
    @ManyToMany(mappedBy = "units")
    private Set<Army> armies = new HashSet<>();

    public void addAction(Action action) {
        actions.add(action);
        action.setUnit(this);
    }
    public void removeAction(Action action) {
        actions.remove(action);
        action.setUnit(null);
    }

    public void addArmy(Army army) {
        this.armies.add(army);
        army.getUnits().add(this);
    }
    public void removeArmy(Army army) {
        this.armies.remove(army);
        army.getUnits().remove(this);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
