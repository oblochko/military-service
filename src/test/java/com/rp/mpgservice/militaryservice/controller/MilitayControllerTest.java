package com.rp.mpgservice.militaryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.mpgservice.militaryservice.dto.RequestMove;
import com.rp.mpgservice.militaryservice.dto.NewPosDto;
import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.repository.ArmyMoveRepository;
import com.rp.mpgservice.militaryservice.repository.ArmyRepository;
import com.rp.mpgservice.militaryservice.util.ArmyUtils;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.rp.mpgservice.militaryservice.constant.NameState.DASKIA;
import static com.rp.mpgservice.militaryservice.constant.NameState.LIKONIA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class MilitayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ArmyMoveRepository armyMoveRepository;

    @Autowired
    ArmyRepository armyRepository;

    private final String armyMoveURl = "/api/military/{idState}/armyMove";

    private final String completeTheMoveURL = "/api/military/completeTheMove";

    @BeforeEach
    public void loadBd() {
        armyMoveRepository.deleteAll();
        armyRepository.deleteAll();
        List<Army> armies = ArmyUtils.getPosArmiesByState1();
        armies.get(1).setAttack(2);
        armyRepository.saveAll(armies);
    }

    @Test
    public void completeTheMoveTest() throws Exception {
        armyMoveRepository.deleteAll();
        armyRepository.deleteAll();
        List<Army> armies = ArmyUtils.getPosArmiesByState1();
        armies.get(1).setAttack(2);
        armies = armyRepository.saveAll(armies);


        Long id1 = armies.get(0).getId();
        Long id2 = armies.get(1).getId();

        String id1State = LIKONIA;
        String id2State = DASKIA;

        List<RequestMove> armyMoveList = ArmyUtils.getNewPosArmiesByState1();
        armyMoveList.get(0).setId(id1);
        armyMoveList.get(1).setId(id2);
        String requestMove1L = objectMapper.writeValueAsString(armyMoveList.get(0));
        String requestMove2L = objectMapper.writeValueAsString(armyMoveList.get(1));
        mockMvc.perform(
                        MockMvcRequestBuilders.post(armyMoveURl, id1State)
                                .content(requestMove1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(armyMoveURl, id2State)
                                .content(requestMove2L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        NewPosDto responseNewPos = new NewPosDto(id2, DASKIA, 4L);
        List<NewPosDto> responseNewPoses = new ArrayList<>(List.of(responseNewPos));

        String actualResponseNewPos = objectMapper.writeValueAsString(responseNewPoses);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(completeTheMoveURL))
                .andExpect(status().isOk())
                .andExpect(content().json(actualResponseNewPos));
        List<Army> armiesPrint = armyRepository.findAll();
        armiesPrint.size();
    }

    @AfterEach
    public void resetDb() {
        armyMoveRepository.deleteAll();
        armyRepository.deleteAll();
    }
}
