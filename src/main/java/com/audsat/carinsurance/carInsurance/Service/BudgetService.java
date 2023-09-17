package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.BaseValueRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.CarHasClaimsRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.DriverHasClaimsRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.PrincipalDriverAgeRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Response.BudgetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class BudgetService {


    private final BaseValueRule baseValueRule;
    private final PrincipalDriverAgeRule principalDriverAgeRule;
    private final DriverHasClaimsRule DriverHasClaimsRule;
    private final CarHasClaimsRule CarHasClaimsRule;


    public BudgetResponse calculateInsurance(InsuranceEntity insuranceEntity) {

        baseValueRule
                .setNextRule(principalDriverAgeRule)
                .setNextRule(DriverHasClaimsRule)
                .setNextRule(CarHasClaimsRule);

        BudgetDto budgetDto = BudgetDto.builder()
                .insuranceEntity(insuranceEntity)
                .build();

        baseValueRule.handle(budgetDto);

        return buildResponse(budgetDto);

    }

    private BudgetResponse buildResponse(BudgetDto budgetDto) {

        Float carFipeValue = budgetDto.getInsuranceEntity().getCar().getFipeValue();

        Float valueInsurance = (carFipeValue * budgetDto.getPercentage()) / 100;

        return BudgetResponse.builder()
                .percentage(budgetDto.getPercentage())
                .value(valueInsurance)
                .build();
    }
}
