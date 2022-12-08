package com.rp.mpgservice.militaryservice.entity;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.TypeMove;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Table(name = "army_movements")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ArmyMove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long idArmy;
    TypeMove typeMove;
    int additionalAttack;
    Long moveId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyMove armyMove = (ArmyMove) o;
        return Objects.equals(idArmy, armyMove.idArmy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArmy);
    }

    public static ArmyMove makeDefaultByRequestArmy(RequestMove requestMove) {
        return ArmyMove.builder()
                .idArmy(requestMove.getId())
                .typeMove(requestMove.getTypeMove())
                .additionalAttack(requestMove.getAdditionalAttack())
                .moveId(requestMove.getMoveId())
                .build();
    }
}
