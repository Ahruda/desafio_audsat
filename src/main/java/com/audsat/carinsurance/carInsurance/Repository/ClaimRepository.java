package com.audsat.carinsurance.carInsurance.Repository;

import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<ClaimEntity, Long> {

    List<ClaimEntity> findByDriverId(Long driverId);

    List<ClaimEntity> findByCarId(Long carId);

}
