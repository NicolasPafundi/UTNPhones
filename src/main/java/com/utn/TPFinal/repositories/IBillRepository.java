package com.utn.TPFinal.repositories;

import com.utn.TPFinal.domain.Entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {
}