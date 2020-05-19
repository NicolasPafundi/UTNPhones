package com.utn.TPFinal.repositories;

import com.utn.TPFinal.domain.Entities.Call;
import com.utn.TPFinal.domain.Projections.UserCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {

    @Query(value = "CALL sp_llamadasxfechaxusuario(:datefrom,:dateto,:userid);", nativeQuery = true)
    List<UserCall> getByUser(@Param("datefrom") Date dateFrom, @Param("dateto") Date dateTo, @Param("userid") Integer userId);

}