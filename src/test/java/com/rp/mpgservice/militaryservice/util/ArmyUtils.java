package com.rp.mpgservice.militaryservice.util;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.TypeMove;
import com.rp.mpgservice.militaryservice.entity.Army;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.rp.mpgservice.militaryservice.constant.NameState.DASKIA;
import static com.rp.mpgservice.militaryservice.constant.NameState.LIKONIA;

@UtilityClass
public class ArmyUtils {

    public  List<RequestMove> getNewPosArmiesByState1() {
        return List.of(
                makeReqMove(1L, 4L),
                makeReqMove(2L, 4L)
        );
    }

    public  List<RequestMove> getNewPosArmiesByState2() {
        return List.of(
                makeReqMove(1L, 4L),
                makeReqMove(2L, 4L),
                makeReqMoveTypeSup(3L, 4L)
        );
    }

    public  List<Army> getPosArmiesByState1() {
        return List.of(
                makeArmy(1L, 2L, LIKONIA),
                makeArmy(2L, 7L, DASKIA)
        );
    }

    public  List<Army> getPosArmiesByState2() {
        return List.of(
                makeArmy(1L, 2L, LIKONIA),
                makeArmy(2L, 7L, DASKIA),
                makeArmy(3L, 8L, DASKIA)
        );
    }

    public static Army makeArmy(Long id, Long pos, String nameState) {
        return Army.builder()
                .id(id)
                .posId(pos)
                .attack(1)
                .move(1)
                .nameState(nameState)
            .build();
    }

    public static RequestMove makeReqMove(Long id, Long moveId) {
        return RequestMove.builder()
                .id(id)
                .moveId(moveId)
                .typeMove(TypeMove.ATTACK)
                .additionalAttack(1)
            .build();
    }

    public static RequestMove makeReqMoveTypeSup(Long id, Long moveId) {
        return RequestMove.builder()
                .id(id)
                .moveId(moveId)
                .typeMove(TypeMove.SUPPORT)
                .additionalAttack(1)
                .build();
    }

}
