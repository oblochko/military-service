package com.rp.mpgservice.militaryservice.service;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.ResponseMove;
import com.rp.mpgservice.militaryservice.dto.NewPosDto;
import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.entity.ArmyMove;
import com.rp.mpgservice.militaryservice.repository.ArmyMoveRepository;
import com.rp.mpgservice.militaryservice.repository.ArmyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class MilitaryService {

    @Autowired
    ArmyRepository armyRepository;

    @Autowired
    ArmyMoveRepository armyMoveRepository;

    @Transactional
    public List<NewPosDto> completeTheMove() {
        List<Army> armyList = armyRepository.findAll();
        List<RequestMove> armyMoveList = armyMoveRepository.findAll().stream()
                .map(RequestMove::makeDefaultByArmyMove)
                .collect(Collectors.toList());

        ResponseMove responseMove = new MilitaryEngine().armyMove(armyMoveList, armyList);
        armyRepository.deleteAllById(responseMove.getIdDeleteArmies());
        armyRepository.saveAll(responseMove.getNewPosArmies());
        armyMoveRepository.deleteAll();
        return responseMove.getNewPosArmies().stream()
                .map(NewPosDto::makeDefaultByArmy)
                .collect(Collectors.toList());
    }

    public void shouldCreateArmy(Army army) {
        armyRepository.save(army);
    }

    public void shouldCreateArmies(List<Army> armies) {
        armyRepository.saveAll(armies);
    }


    @Transactional
    public void armyMove(String nameState, RequestMove requestMove) throws Exception {
        if(!armyRepository.existsByIdAndStateName(requestMove.getId(), nameState))
            throw new Exception();

        Optional<ArmyMove> armyMove = armyMoveRepository.findByArmyId(requestMove.getId());
        if(armyMove.isPresent()) {
            armyMove.get().setMoveId(requestMove.getMoveId());
            //armyMove.get().setTypeMove(requestMove.getTypeMove());
            //armyMove.get().setAdditionalAttack(requestMove.getAdditionalAttack());
            armyMoveRepository.save(armyMove.get());
            return;
        }
        ArmyMove armyNewMove = ArmyMove.makeDefaultByRequestArmy(requestMove);
        armyMoveRepository.save(armyNewMove);
    }

    public List<Army> getArmies() {
        return armyRepository.findAll();
    }
}
