package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends JpaRepository<Rate, Integer> {
}
