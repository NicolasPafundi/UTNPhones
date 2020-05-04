package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTypeRepository extends JpaRepository<UserType, Integer> {
}