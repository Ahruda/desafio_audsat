package com.audsat.carinsurance.carInsurance.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
@Table(name = "insurances")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull
    @Column(name = "creation_dt")
    private Timestamp creationDt;

    @NotNull
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @NotNull
    @ManyToOne
    private CarEntity car;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;

}
