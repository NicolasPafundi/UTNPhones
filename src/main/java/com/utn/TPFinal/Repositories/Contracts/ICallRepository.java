package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {
}