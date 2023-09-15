package com.audsat.carinsurance.carInsurance.Repository;

import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

}
