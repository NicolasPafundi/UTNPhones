package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {

}