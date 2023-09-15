package com.audsat.carinsurance.carInsurance.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "car_drivers")
public class CarDriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private DriverEntity driver;

    @NotNull
    @ManyToOne
    private CarEntity car;

    @NotNull
    @Column(name = "is_main_driver")
    private Boolean isMainDriver;

}
