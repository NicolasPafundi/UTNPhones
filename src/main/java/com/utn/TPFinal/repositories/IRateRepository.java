package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends JpaRepository<Rate, Integer> {
}
