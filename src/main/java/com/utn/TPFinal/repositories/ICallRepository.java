package com.utn.TPFinal.repositories;

import com.sun.istack.Nullable;
import com.utn.TPFinal.model.dtos.CallsReportFilter;
import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.projections.InfraResponse;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.ReportCallsByUserByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;


@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {

    @Query(value = "CALL sp_CallsByUser(:userid);", nativeQuery = true)
    List<Call> getByUserId(@Param("userid") Integer userId);

    @Query(value = " CALL sp_add_call(:numberlinefrom, :numberlineto, :durationmin, :datecall);", nativeQuery = true)
    InfraResponse createCall(@Param("numberlinefrom") Integer numberFrom, @Param("numberlineto") Integer numberTo , @Param("durationmin") double duration, @Param("datecall") Date callDate);

    @Query(value = " CALL sp_reportCallsByUserByDate(:userid,:datefrom,:dateto);", nativeQuery = true)
    List<ReportCallsByUserByDate> getReportCallsByUserByDate(@Param("userid") Integer userId,@Param("datefrom") Date dateFrom, @Param("dateto") Date dateTo );

}