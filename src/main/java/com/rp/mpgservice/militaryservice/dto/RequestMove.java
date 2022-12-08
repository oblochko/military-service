package com.rp.mpgservice.militaryservice.dto;

import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.entity.ArmyMove;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestMove {
    Long id;
    TypeMove typeMove;
    int additionalAttack;
    Long moveId;

    public static RequestMove makeDefaultByArmy(Army army) {
        return RequestMove.builder()
                .id(army.getId())
                .typeMove(TypeMove.DEFENSE)
                .additionalAttack(0)
                .moveId(army.getPosId())
                .build();
    }

    public static RequestMove makeDefaultByArmyMove(ArmyMove armyMove) {
        return RequestMove.builder()
                .id(armyMove.getIdArmy())
                .typeMove(armyMove.getTypeMove())
                .additionalAttack(armyMove.getAdditionalAttack())
                .moveId(armyMove.getMoveId())
                .build();
    }
}
