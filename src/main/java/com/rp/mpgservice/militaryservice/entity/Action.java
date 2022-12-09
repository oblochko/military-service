package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
    private Unit unit;
}
