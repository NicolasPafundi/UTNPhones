package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {

    @Query(value = "CALL sp_CallsByUser(:userid);", nativeQuery = true)
    List<Call> getByUserId(@Param("userid") Integer userId);
}