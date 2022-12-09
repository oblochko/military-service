package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Table(name = "action")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Action {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer damage;

    private String typeActionName;

    @ManyToOne
    private Unit unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(name, action.name) && Objects.equals(damage, action.damage) && Objects.equals(typeActionName, action.typeActionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, damage, typeActionName);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", typeActionName='" + typeActionName + '\'' +
                '}';
    }
}
