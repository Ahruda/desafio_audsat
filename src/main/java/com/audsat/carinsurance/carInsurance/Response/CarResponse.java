package com.audsat.carinsurance.carInsurance.Response;

import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static jakarta.persistence.FetchType.EAGER;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Long id;

    private String model;

    private String manufacturer;

    private String modelYear;

    private Float fipeValue;

}
