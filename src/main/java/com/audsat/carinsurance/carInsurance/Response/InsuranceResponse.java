package com.audsat.carinsurance.carInsurance.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponse {

    private Long id;

    private Boolean isActive;

    private Instant creationDt;

    private Instant updatedAt;

    private BudgetResponse budget;

    private CustomerResponse customer;

    private CarResponse car;

}
