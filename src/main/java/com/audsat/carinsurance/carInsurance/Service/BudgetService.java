package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.BaseValueRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.CarHasClaimsRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.DriverHasClaimsRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.MainDriverAgeRule;
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
    private final MainDriverAgeRule mainDriverAgeRule;
    private final DriverHasClaimsRule DriverHasClaimsRule;
    private final CarHasClaimsRule CarHasClaimsRule;

    public BudgetResponse calculateInsurance(InsuranceEntity insuranceEntity) {

        log.info("I=Iniciando_calculo_do_orcamento InsuranceEntity={}", insuranceEntity);

        baseValueRule
                .setNextRule(mainDriverAgeRule)
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

        log.info("I=Valor_do_orcamento_calculado Percentage={} ValueInsurance={}", budgetDto.getPercentage(), valueInsurance);

        return BudgetResponse.builder()
                .percentage(budgetDto.getPercentage())
                .value(valueInsurance)
                .build();
    }
}
