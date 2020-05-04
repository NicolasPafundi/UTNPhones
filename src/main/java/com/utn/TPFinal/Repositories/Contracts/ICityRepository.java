package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Integer> {
}