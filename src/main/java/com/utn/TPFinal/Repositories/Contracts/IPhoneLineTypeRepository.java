package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.PhoneLineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneLineTypeRepository extends JpaRepository<PhoneLineType, Integer> {
}