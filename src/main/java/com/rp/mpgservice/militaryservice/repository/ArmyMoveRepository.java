package com.rp.mpgservice.militaryservice.repository;

import com.rp.mpgservice.militaryservice.entity.ArmyMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmyMoveRepository extends JpaRepository<ArmyMove, Long> {
    Optional<ArmyMove> findByIdArmy(Long id);
}
