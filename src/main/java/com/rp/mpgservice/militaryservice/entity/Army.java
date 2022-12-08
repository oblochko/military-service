package com.rp.mpgservice.militaryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "armies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long posId;

    private int attack;
    private int move;

    private String nameState;
}
