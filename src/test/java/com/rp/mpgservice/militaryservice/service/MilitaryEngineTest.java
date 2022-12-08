package com.rp.mpgservice.militaryservice.service;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.ResponseMove;
import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.util.ArmyUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MilitaryEngineTest {

    MilitaryEngine militaryEngine = new MilitaryEngine();

    @Test
    public void armyMoveState1Test() {
        List<Army> armies = ArmyUtils.getPosArmiesByState1();

        armies.stream().mapToLong(Army::getId).sum();
        List<RequestMove> armyMoveList = ArmyUtils.getNewPosArmiesByState1();
        ResponseMove responseMove = militaryEngine.armyMove(armyMoveList, armies);
        List<Army> newPos = responseMove.getNewPosArmies();
        Long posArmyById1L = newPos.stream()
                .filter(army -> army.getId().equals(1L))
                .findFirst().get().getPosId();
        Long posArmyById2L = newPos.stream()
                .filter(army -> army.getId().equals(2L))
                .findFirst().get().getPosId();
        Assertions.assertEquals(0, responseMove.getIdDeleteArmies().size());
        Assertions.assertEquals(2, posArmyById1L);
        Assertions.assertEquals(7, posArmyById2L);
    }

    @Test
    public void armyMoveState2Test() {
        List<Army> armies = ArmyUtils.getPosArmiesByState1();
        armies.get(1).setAttack(2);
        List<RequestMove> armyMoveList = ArmyUtils.getNewPosArmiesByState1();
        ResponseMove responseMove = militaryEngine.armyMove(armyMoveList, armies);
        List<Army> newPos = responseMove.getNewPosArmies();
        Long posArmyById2L = newPos.stream()
                .filter(army -> army.getId().equals(2L))
                .findFirst().get().getPosId();
        Assertions.assertEquals(1, responseMove.getIdDeleteArmies().size());
        Assertions.assertEquals(4, posArmyById2L);
    }

    @Test
    public void armyMoveState3Test() {
        List<Army> armies = ArmyUtils.getPosArmiesByState2();
        List<RequestMove> armyMoveList = ArmyUtils.getNewPosArmiesByState2();
        ResponseMove responseMove = militaryEngine.armyMove(armyMoveList, armies);
        List<Army> newPos = responseMove.getNewPosArmies();
        Long posArmyById2L = newPos.stream()
                .filter(army -> army.getId().equals(2L))
                .findFirst().get().getPosId();
        Long posArmyById3L = newPos.stream()
                .filter(army -> army.getId().equals(3L))
                .findFirst().get().getPosId();
        Assertions.assertEquals(1, responseMove.getIdDeleteArmies().size());
        Assertions.assertEquals(4, posArmyById2L);
        Assertions.assertEquals(8, posArmyById3L);
    }

}
