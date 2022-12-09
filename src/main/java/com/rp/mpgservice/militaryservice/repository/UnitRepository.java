package com.rp.mpgservice.militaryservice.repository;

import com.rp.mpgservice.militaryservice.entity.Army;
import com.rp.mpgservice.militaryservice.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

}
