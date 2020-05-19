package com.utn.TPFinal.repositories;

import com.utn.TPFinal.domain.Entities.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneLineRepository extends JpaRepository<PhoneLine, Integer> {
}
