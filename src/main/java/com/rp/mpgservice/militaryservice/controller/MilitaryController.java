package com.rp.mpgservice.militaryservice.controller;

import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.NewPosDto;
import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.service.MilitaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/military")
//@RequiredArgsConstructor
public class MilitaryController {

    @Autowired
    MilitaryService militaryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/army")
    public void shouldCreateArmy(@RequestBody Army army) {
        militaryService.shouldCreateArmy(army);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/armies")
    public void shouldCreateArmies(@RequestBody List<Army> armies) {
        militaryService.shouldCreateArmies(armies);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/armies")
    public List<Army> getArmies() {
        return militaryService.getArmies();
    }

    @PostMapping("/completeTheMove")
    @ResponseStatus(HttpStatus.OK)
    public List<NewPosDto> completeTheMove() {
        return militaryService.completeTheMove();
    }

    @PostMapping("/{nameState}/armyMove")
    @ResponseStatus(HttpStatus.OK)
    public void armyMove(@PathVariable String nameState, @RequestBody RequestMove requestMove) throws Exception {
        militaryService.armyMove(nameState, requestMove);
    }
}
