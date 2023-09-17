package com.audsat.carinsurance.carInsurance.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long carId;

    @NotNull
    private Boolean isActive;

}
