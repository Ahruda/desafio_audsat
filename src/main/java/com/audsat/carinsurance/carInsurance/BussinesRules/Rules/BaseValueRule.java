package com.audsat.carinsurance.carInsurance.BussinesRules.Rules;

import com.audsat.carinsurance.carInsurance.BussinesRules.AbstractInsuranceBudgetPercentageHandle;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BaseValueRule extends AbstractInsuranceBudgetPercentageHandle {

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        log.info("I=Iniciando_verificacao_da_regra_BaseValueRule");

        budgetDto.setPercentage(6);

        log.info("I=Enquadrado_na_regra_BaseValueRule");

        return super.handle(budgetDto);
    }

}
