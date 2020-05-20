package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Call;
import com.utn.TPFinal.model.projections.MobileReportUserBills;
import com.utn.TPFinal.model.projections.MobileReportUserCalls;
import com.utn.TPFinal.model.projections.MobileReportUserCallsRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface IMobileReportRepository extends JpaRepository<Call,Integer> {

    @Query(value = "CALL sp_getCallsByUserByDate(:datefrom,:dateto,:userid);", nativeQuery = true)
    List<MobileReportUserCalls> getCallsByUserByDate(@Param("datefrom") Date dateFrom, @Param("dateto") Date dateTo, @Param("userid") Integer userId);

    @Query(value = "CALL sp_getBillsByUserByDate(:datefrom,:dateto,:userid);", nativeQuery = true)
    List<MobileReportUserBills> getBillsByUserByDate(@Param("datefrom") Date dateFrom, @Param("dateto") Date dateTo, @Param("userid") Integer userId);

    @Query(value = "CALL sp_getDestinationRankByUser(:userid);", nativeQuery = true)
    List<MobileReportUserCallsRank> getDestinationRankByUser(@Param("userid") Integer userId);


}
