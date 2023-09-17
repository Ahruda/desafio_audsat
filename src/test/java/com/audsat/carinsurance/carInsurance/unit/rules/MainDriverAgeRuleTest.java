package com.audsat.carinsurance.carInsurance.unit.rules;


import com.audsat.carinsurance.carInsurance.BussinesRules.Rules.MainDriverAgeRule;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
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

import java.time.LocalDate;
import java.util.Calendar;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class MainDriverAgeRuleTest {

    @InjectMocks
    private MainDriverAgeRule mainDriverAgeRule;
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
    public void shouldIncrease2PercentageIfMainCarDriverHasDangerousAge() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2025);
        c.set(Calendar.MONTH, Calendar.DECEMBER);
        c.set(Calendar.DAY_OF_MONTH, 31);

        insuranceEntity.setCreationDt(c.toInstant());

        driver.setBirthdate(LocalDate.of(2000, 1, 1));

        CarDriverEntity mainCarDriver = CarDriverEntity.builder()
                .driver(driver)
                .build();

        Mockito.when(carDriverService.findMainDriverByCarId(anyLong()))
                .thenReturn(mainCarDriver);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        mainDriverAgeRule.handle(budgetDto);

        Assertions.assertEquals(2, budgetDto.getPercentage());
    }

    @Test
    public void shouldIncrease2PercentageIfMainCarDriverHasNoDangerousAge() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2026);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);

        insuranceEntity.setCreationDt(c.toInstant());

        driver.setBirthdate(LocalDate.of(2000, 1, 1));

        CarDriverEntity mainCarDriver = CarDriverEntity.builder()
                .driver(driver)
                .build();

        Mockito.when(carDriverService.findMainDriverByCarId(anyLong()))
                .thenReturn(mainCarDriver);

        BudgetDto budgetDto = BudgetDto.builder().insuranceEntity(insuranceEntity).percentage(0).build();

        mainDriverAgeRule.handle(budgetDto);

        Assertions.assertEquals(0, budgetDto.getPercentage());

    }
}
