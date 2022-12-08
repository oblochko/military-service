package com.rp.mpgservice.militaryservice.service;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.ResponseMove;
import com.rp.mpgservice.militaryservice.dto.TypeMove;
import com.rp.mpgservice.militaryservice.entity.Army;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class MilitaryEngine {

    private Map<Long, Army> mapArmies = new HashMap<>();
    private Map<Long, Army> posMap = new HashMap<>();
    private Map<Long, List<RequestMove>> nextPosArmy = new HashMap<>();
    private List<Army> newPosArmies = new ArrayList<>();

    private List<Long> idDeleteArmies = new ArrayList();

    public ResponseMove armyMove(List<RequestMove> armyMoveList, List<Army> armyList) {
        init(armyMoveList, armyList);
        strokeComplement(armyMoveList);
        fightInCages();

        return ResponseMove.builder()
                .newPosArmies(newPosArmies)
                .idDeleteArmies(idDeleteArmies)
                .build();
    }

    /**
     * armyList - список изначальных позиций армий
     * armyMoveList - список новых позиций армий
     */
    private void init(List<RequestMove> armyMoveList, List<Army> armyList) {
        armyList.forEach(
                army -> posMap.put(army.getPosId(), army)
        );

        armyMoveList.forEach(
                army ->
                        put(army.getMoveId(), army)
        );

        armyList.forEach(
                army -> mapArmies.put(army.getId(), army)
        );
    }

    /**
     * дополняем мапу новых позиций фишками, которые не передвинулись
     */
    private void strokeComplement(List<RequestMove> armyMoveList) {
        List<Long> idList = armyMoveList.stream()
                .map(RequestMove::getId)
                .collect(Collectors.toList());
        mapArmies.keySet().forEach(
                key -> {
                    if(idList.stream().noneMatch(id -> id.equals(key))) {
                        put(mapArmies.get(key).getPosId(), RequestMove.makeDefaultByArmy(mapArmies.get(key)));
                    }
                }
        );
    }

    private void fightInCages() {
        nextPosArmy.keySet().forEach(this::fightInCage);
    }

    private void fightInCage(Long posId) {
        List<RequestMove> armies = nextPosArmy.get(posId);
        if(armies == null)
            return;
        if(armies.size() == 1) {
            winnerPosition(armies.get(0));
            return;
        }
        List<Alliance> alliances = groupingIntoAnAlliance(armies);
        if(isStandoff(alliances)) {
            armies.forEach(this::standoffPosition);
            return;
        }
        Alliance alliance = alliances.stream()
                .max(Comparator.comparingInt(Alliance::getPoints)
        ).get();

        List<RequestMove> winnersArmies = armies.stream()
                .filter(army -> mapArmies.get(army.getId()).getNameState().equals(alliance.getNameState()))
                .collect(Collectors.toList());

        winnersArmies.forEach(this::winnerPosition);

        List<RequestMove> loseArmies = armies.stream()
                .filter(army -> !mapArmies.get(army.getId()).getNameState().equals(alliance.getNameState()))
                .collect(Collectors.toList());

        idDeleteArmies.addAll(
                loseArmies.stream()
                        .map(RequestMove::getId)
                        .collect(Collectors.toList())
        );
    }

    private boolean isStandoff(List<Alliance> alliances) {
        int points = alliances.get(0).points;
        for(int i = 1; i < alliances.size(); i++)
            if(alliances.get(i).getPoints() != points)
                return false;
        return true;
    }

    private List<Alliance> groupingIntoAnAlliance(List<RequestMove> armies) {
        Map<String, Integer> mapAlliance = new HashMap<>();
        armies.forEach(army -> {
                    String nameState = mapArmies.get(army.getId()).getNameState();
                    Integer point = mapAlliance.get(nameState);
                    if(point == null) {
                        mapAlliance.put(nameState, mapArmies.get(army.getId()).getAttack());
                    } else {
                        mapAlliance.put(nameState, mapArmies.get(army.getId()).getAttack() + point);
                    }
                });
        return mapAlliance.keySet().stream()
                .map(key -> new Alliance(key, mapAlliance.get(key)))
                .collect(Collectors.toList());
    }


    private void winnerPosition(RequestMove move) {
        Army lastArmy = mapArmies.get(move.getId());
        Long posId;
        if(move.getTypeMove().equals(TypeMove.DEFENSE) || move.getTypeMove().equals(TypeMove.SUPPORT))
            posId = lastArmy.getPosId();
        else
            posId = move.getMoveId();
        Army army = Army.builder()
                .id(move.getId())
                .posId(posId)
                .attack(lastArmy.getAttack())
                .move(lastArmy.getMove())
                .nameState(lastArmy.getNameState())
                .build();
        newPosArmies.add(army);
    }

    private void standoffPosition(RequestMove move) {
        Army lastArmy = mapArmies.get(move.getId());
        Army army = Army.builder()
                .id(move.getId())
                .posId(lastArmy.getPosId())
                .attack(lastArmy.getAttack())
                .move(lastArmy.getMove())
                .nameState(lastArmy.getNameState())
                .build();
        newPosArmies.add(army);
    }

    private void put(Long id, RequestMove army) {
        List<RequestMove> listRequestMove = nextPosArmy.get(army.getMoveId());
        if (listRequestMove == null) {
            listRequestMove = new ArrayList<>();
            listRequestMove.add(army);
            nextPosArmy.put(id, listRequestMove);
            return;
        }
        listRequestMove.add(army);
    }

    private Army getArmyById(Long id) {
        return posMap.values().stream()
                .filter(army -> army.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Data
    @AllArgsConstructor
    public static class Alliance {
        String nameState;
        Integer points;
    }

}
