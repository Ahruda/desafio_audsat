package com.audsat.carinsurance.carInsurance.unit.rules;


import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.PrincipalDriverAgeRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Service.CarDriverService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class PrincipalDriverAgeRuleTest {


    @InjectMocks
    private PrincipalDriverAgeRule principalDriverAgeRule;

    @Mock
    private CarDriverService carDriverService;

    private InsuranceEntity insuranceEntity;
    private DriverEntity driver;

    @BeforeEach
    public void buildInsurance() {

        driver = DriverEntity.builder()
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
                .build();
    }

    @Test
    public void shouldIncrease2PercentageIfMainCarDriverHasDangerousAge() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        driver.setBirthdate(dateFormat.parse("01/01/2000"));
        insuranceEntity.setCreationDt(dateFormat.parse("31/12/2025").toInstant());

        CarDriverEntity mainCarDriver = CarDriverEntity.builder()
                .driver(driver)
                .build();

        Mockito.when(carDriverService.findMainDriverByCarId(anyLong()))
                .thenReturn(mainCarDriver);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        principalDriverAgeRule.handle(budgetDto);

        Assertions.assertEquals(2, budgetDto.getPercentage());
    }

    @Test
    public void shouldIncrease2PercentageIfMainCarDriverHasNoDangerousAge() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        driver.setBirthdate(dateFormat.parse("01/01/2000"));
        insuranceEntity.setCreationDt(dateFormat.parse("01/01/2026").toInstant());

        CarDriverEntity mainCarDriver = CarDriverEntity.builder()
                .driver(driver)
                .build();

        Mockito.when(carDriverService.findMainDriverByCarId(anyLong()))
                .thenReturn(mainCarDriver);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        principalDriverAgeRule.handle(budgetDto);

        Assertions.assertEquals(0, budgetDto.getPercentage());

    }
}
