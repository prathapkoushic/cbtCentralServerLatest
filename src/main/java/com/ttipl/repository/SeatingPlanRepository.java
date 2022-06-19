package com.ttipl.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.SeatingPlan;

@Repository
public interface SeatingPlanRepository extends CrudRepository<SeatingPlan, Integer> 
{
	
    @Query(value="SELECT * FROM seating_plan group by room_name" , nativeQuery = true)
	List<SeatingPlan> findAllByGroup();

    @Query(value="SELECT * FROM seating_plan where room_name=?1" , nativeQuery = true)
    List<SeatingPlan> findByRoom(String roomName);

}
