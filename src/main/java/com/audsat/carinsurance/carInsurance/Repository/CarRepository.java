package com.audsat.carinsurance.carInsurance.Repository;

import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

}
