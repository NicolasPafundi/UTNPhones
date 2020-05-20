package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByUserId(Integer id);
}