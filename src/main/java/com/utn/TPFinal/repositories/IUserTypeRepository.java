package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTypeRepository extends JpaRepository<UserType, Integer> {
}