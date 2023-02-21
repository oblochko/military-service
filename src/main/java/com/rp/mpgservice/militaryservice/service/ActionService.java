package com.rp.mpgservice.militaryservice.service;

import com.rp.mpgservice.militaryservice.dto.ActionDto;
import com.rp.mpgservice.militaryservice.entity.Action;
import com.rp.mpgservice.militaryservice.repository.ActionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Transactional
    public void updateActions(List<ActionDto> actions) {
        for (ActionDto action : actions) {
            actionRepository.updateAction(action.getId(), action.getTypeActionName());
        }
    }
}
