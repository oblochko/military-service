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
}
