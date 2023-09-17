package com.audsat.carinsurance.carInsurance.unit.rules;


import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.BaseValueRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseValueRuleTest {

    @InjectMocks
    private BaseValueRule baseValueRule;

    @Test
    public void shouldInsertBaseValueInDto() {

        BudgetDto budgetDto = BudgetDto.builder().percentage(0).build();

        baseValueRule.handle(budgetDto);

        Assertions.assertEquals(6, budgetDto.getPercentage());
    }

}
