package com.rp.mpgservice.militaryservice.controller;

import com.rp.mpgservice.militaryservice.dto.ActionDto;
import com.rp.mpgservice.militaryservice.entity.Action;
import com.rp.mpgservice.militaryservice.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/actions")
    public void updateActions(@RequestBody List<ActionDto> actions) {
        actionService.updateActions(actions);
    }
}
