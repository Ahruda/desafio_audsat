package com.audsat.carinsurance.carInsurance.BussinesRules.Rules;

import com.audsat.carinsurance.carInsurance.BussinesRules.AbstractInsuranceBudgetPercentageHandle;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Builder
@Component
public class BaseValueRule extends AbstractInsuranceBudgetPercentageHandle {

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        budgetDto.setPercentage(6);

        return super.handle(budgetDto);
    }

}
