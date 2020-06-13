package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.Rate;
import com.utn.TPFinal.model.projections.RatesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IRateRepository extends JpaRepository<Rate, Integer> {

    @Query(value = "CALL sp_ratesBetweenAreaCodes(:areaCodeFrom, :areaCodeTo);", nativeQuery = true)
    List<RatesReport> getRatesBetweenAreaCodes(@Param("areaCodeFrom") Integer areaCodeFrom, @Param("areaCodeTo") Integer areaCodeTo);

}
