package com.rp.mpgservice.militaryservice.repository;

import com.rp.mpgservice.militaryservice.entity.Action;
import com.rp.mpgservice.militaryservice.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Action a SET a.typeActionName = :typeActionName WHERE a.id = :id")
    void updateAction(@Param("id") Long id, @Param("typeActionName") String typeActionName);
}
