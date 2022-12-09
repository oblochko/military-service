package com.rp.mpgservice.militaryservice.entity;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Table(name = "army_move")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ArmyMove {
    @Id
    @GeneratedValue
    Long id;

    Long armyId;

    Long moveId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyMove armyMove = (ArmyMove) o;
        return Objects.equals(armyId, armyMove.armyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(armyId);
    }

    public static ArmyMove makeDefaultByRequestArmy(RequestMove requestMove) {
        return ArmyMove.builder()
                .armyId(requestMove.getId())
                .moveId(requestMove.getMoveId())
                .build();
    }
}
