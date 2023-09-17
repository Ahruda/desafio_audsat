package com.audsat.carinsurance.carInsurance.unit.rules;


import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.DriverHasClaimsRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Service.ClaimService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class DriverHasClaimsRuleTest {

    @InjectMocks
    private DriverHasClaimsRule driverHasClaimsRule;

    @Mock
    private ClaimService claimService;

    private InsuranceEntity insuranceEntity;

    @BeforeEach
    public void buildInsurance() {

        DriverEntity driver = DriverEntity.builder()
                .id(1L)
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

        Mockito.when(claimService.findByDriverId(anyLong())).thenReturn(claimEntitiesList);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        driverHasClaimsRule.handle(budgetDto);

        Assertions.assertEquals(2, budgetDto.getPercentage());
    }

    @Test
    public void shouldNotIncrease2PercentageIfCarHasClaims() {

        List<ClaimEntity> claimEntitiesList = new ArrayList<>();

        Mockito.when(claimService.findByDriverId(anyLong())).thenReturn(claimEntitiesList);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        driverHasClaimsRule.handle(budgetDto);

        Assertions.assertEquals(0, budgetDto.getPercentage());
    }
}
