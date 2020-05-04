package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneLineRepository extends JpaRepository<PhoneLine, Integer> {
}
