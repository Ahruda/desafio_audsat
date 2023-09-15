package com.audsat.carinsurance.carInsurance.Repository;

import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDriverRepository extends JpaRepository<CarDriverEntity, Long> {

}
