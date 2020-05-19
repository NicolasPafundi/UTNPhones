package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.PhoneLineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneLineTypeRepository extends JpaRepository<PhoneLineType, Integer> {
}