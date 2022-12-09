package com.rp.mpgservice.militaryservice.dto;

import com.rp.mpgservice.militaryservice.entity.Army;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPosDto {
    Long idArmies;
    String nameState;
    Long idPos;

    public static NewPosDto makeDefaultByArmy(Army army) {
        return NewPosDto.builder()
                .idArmies(army.getId())
                .nameState(army.getStateName())
                .idPos(army.getPosId())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPosDto that = (NewPosDto) o;
        return Objects.equals(idArmies, that.idArmies) && Objects.equals(idPos, that.idPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArmies, idPos);
    }
}
