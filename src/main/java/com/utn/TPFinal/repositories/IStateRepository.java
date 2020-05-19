package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, Integer> {
}