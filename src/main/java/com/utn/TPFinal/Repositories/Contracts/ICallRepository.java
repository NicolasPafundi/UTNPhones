package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.DTOs.CallInput;
import com.utn.TPFinal.Domain.Entities.Call;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.StoredProcedureParameter;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {
    @Query(value="{utnphones.sp_add_call(:numberlinefrom,:numberlineto,:durationmin)}",nativeQuery = true)
    void New(@Param("numberlinefrom") int numberLineFrom,@Param("numberlineto") int numberLineTo, @Param("durationmin") double durationMin);


}