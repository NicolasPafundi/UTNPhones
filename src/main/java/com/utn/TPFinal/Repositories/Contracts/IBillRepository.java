package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {
}