package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends JpaRepository<City, Integer> {
    @Query(value = "SELECT * FROM cities WHERE state_id = ?1", nativeQuery = true)
    List<City> getAllByState(Integer stateId);

    City findByAreaCode(Integer areaCodeFrom);

}