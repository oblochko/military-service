package com.rp.mpgservice.militaryservice.dto;

import com.rp.mpgservice.militaryservice.entity.Action;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class ActionDto {
    private Long id;

    private String name;

    private Integer damage;

    private String typeActionName;

    public void ActionDot() {

    }

    public static Action makeAction(ActionDto actionDto) {
        Action action = new Action();
        action.setId(actionDto.getId());
        action.setName(actionDto.getName());
        action.setDamage(actionDto.getDamage());
        action.setTypeActionName(actionDto.getTypeActionName());
        return action;
    }
}
