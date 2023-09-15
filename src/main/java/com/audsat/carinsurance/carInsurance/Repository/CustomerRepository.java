package com.audsat.carinsurance.carInsurance.Repository;

import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
