package com.audsat.carinsurance.carInsurance.BussinesRules;

import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;

import static java.util.Objects.nonNull;

public abstract class AbstractInsuranceBudgetPercentageHandle {

    private AbstractInsuranceBudgetPercentageHandle nextRule;

    public AbstractInsuranceBudgetPercentageHandle
    setNextRule(AbstractInsuranceBudgetPercentageHandle rule) {
        this.nextRule = rule;
        return rule;
    }

    public BudgetDto handle(BudgetDto budgetDto) {

        if(nonNull(nextRule)){
            nextRule.handle(budgetDto);
        }

        return budgetDto;
    }
}
