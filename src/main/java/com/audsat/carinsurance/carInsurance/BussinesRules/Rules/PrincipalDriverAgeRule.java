package com.audsat.carinsurance.carInsurance.BussinesRules.Rules;

import com.audsat.carinsurance.carInsurance.BussinesRules.AbstractInsuranceBudgetPercentageHandle;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Service.CarDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class PrincipalDriverAgeRule extends AbstractInsuranceBudgetPercentageHandle {

    private final CarDriverService carDriverService;

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        log.info("I=Iniciando_verificacao_da_regra_PrincipalDriverAgeRule");

        Long carId = budgetDto.getInsuranceEntity().getCar().getId();

        CarDriverEntity mainCarDriver = carDriverService.findMainDriverByCarId(carId);

        LocalDate birthdate = mainCarDriver.getDriver().getBirthdate();
        Instant dateReference = budgetDto.getInsuranceEntity().getCreationDt();

        int age = calcAge(birthdate, dateReference);

        if(age >= 18 && age < 26) {
            budgetDto.increasePercentage(2);
            log.info("I=Enquadrado_na_regra_PrincipalDriverAgeRule");
        }

        return super.handle(budgetDto);
    }

    private Integer calcAge(LocalDate birthDate, Instant instantReference) {

        LocalDate dateReference = LocalDate.ofInstant(instantReference, ZoneId.systemDefault());

        return Period.between(birthDate, dateReference).getYears();

    }
}
