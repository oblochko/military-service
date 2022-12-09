package com.rp.mpgservice.militaryservice.repository;

import com.rp.mpgservice.militaryservice.entity.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyRepository extends JpaRepository<Army, Long> {
    boolean existsByIdAndStateName(Long id, String stateName);
}
