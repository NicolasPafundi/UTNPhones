package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhoneLineRepository extends JpaRepository<PhoneLine, Integer> {
    @Query(value = "SELECT * FROM phonelines WHERE user_id= ?1", nativeQuery = true)
    List<PhoneLine> getByUser(Integer userId);
}