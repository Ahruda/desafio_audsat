package com.audsat.carinsurance.carInsurance.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BudgetResponse {

    private final Integer percentage;

    private final Float value;

}
