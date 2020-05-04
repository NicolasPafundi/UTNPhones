package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, Integer> {
}