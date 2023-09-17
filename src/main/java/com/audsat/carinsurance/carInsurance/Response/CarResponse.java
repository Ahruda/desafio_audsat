package com.audsat.carinsurance.carInsurance.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
