package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL)
    private Action action;
    @ManyToMany(mappedBy = "units")
    private Set<Army> armies = new HashSet<>();
}
