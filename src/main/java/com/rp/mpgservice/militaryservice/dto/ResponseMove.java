package com.rp.mpgservice.militaryservice.dto;

import com.rp.mpgservice.militaryservice.entity.Army;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMove {
    List<Army> newPosArmies;
    List<Long> idDeleteArmies;
}
