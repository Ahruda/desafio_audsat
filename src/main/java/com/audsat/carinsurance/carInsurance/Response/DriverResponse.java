package com.audsat.carinsurance.carInsurance.Response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {

    private Long id;

    private String document;

    private Date birthdate;

}
