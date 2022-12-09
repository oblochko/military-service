package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "army")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Army {

    @Id
    @GeneratedValue
    private Long id;

    private Long posId;
    private String stateName;

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "army_unit",
            joinColumns = @JoinColumn(name = "army_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private Set<Unit> units = new HashSet<>();

    public void addUnit(Unit unit) {
        this.units.add(unit);
        unit.getArmies().add(this);
    }
    public void removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.getArmies().remove(this);
    }

    @Override
    public String toString() {
        return "Army{" +
                "id=" + id +
                ", posId=" + posId +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
