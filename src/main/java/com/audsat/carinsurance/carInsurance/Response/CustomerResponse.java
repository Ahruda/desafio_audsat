package com.audsat.carinsurance.carInsurance.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {


    private Long id;

    private String name;

    private DriverResponse driver; //todo alterar

}
