package com.audsat.carinsurance.carInsurance.unit.rules;


import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.BaseValueRule;
import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.CarHasClaimsRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Service.ClaimService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class CarHasClaimsRuleTest {

    @InjectMocks
    private CarHasClaimsRule carHasClaimsRule;

    @Mock
    private ClaimService claimService;

    private InsuranceEntity insuranceEntity;

    @BeforeEach
    public void buildInsurance() {

        DriverEntity driver = DriverEntity.builder()
                .build();

        CustomerEntity customer = CustomerEntity.builder()
                .driver(driver)
                .build();

        CarEntity car = CarEntity.builder()
                .id(1L)
                .build();

        insuranceEntity = InsuranceEntity.builder()
                .id(1L)
                .car(car)
                .customer(customer)
                .isActive(Boolean.TRUE)
                .creationDt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    public void shouldIncrease2PercentageIfCarHasClaims() {

        List<ClaimEntity> claimEntitiesList = new ArrayList<>();
        claimEntitiesList.add(new ClaimEntity());

        Mockito.when(claimService.findByCarId(anyLong())).thenReturn(claimEntitiesList);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        carHasClaimsRule.handle(budgetDto);

        Assertions.assertEquals(2, budgetDto.getPercentage());
    }

    @Test
    public void shouldNotIncrease2PercentageIfCarHasClaims() {

        List<ClaimEntity> claimEntitiesList = new ArrayList<>();

        Mockito.when(claimService.findByCarId(anyLong())).thenReturn(claimEntitiesList);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        carHasClaimsRule.handle(budgetDto);

        Assertions.assertEquals(0, budgetDto.getPercentage());
    }
}
