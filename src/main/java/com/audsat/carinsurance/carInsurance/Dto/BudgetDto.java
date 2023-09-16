package com.audsat.carinsurance.carInsurance.Dto;

import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetDto {

    private Integer percentage;

    private InsuranceEntity insuranceEntity;

    public void increasePercentage(Integer value) {
        percentage += value;
    }

}

