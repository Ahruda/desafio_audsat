package com.audsat.carinsurance.carInsurance.BussinesRules.Rules;

import com.audsat.carinsurance.carInsurance.BussinesRules.AbstractInsuranceBudgetPercentageHandle;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Service.CarDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class PrincipalDriverAgeRule extends AbstractInsuranceBudgetPercentageHandle {


    private final CarDriverService carDriverService;

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        Long carId = budgetDto.getInsuranceEntity().getCar().getId();

        CarDriverEntity mainCarDriver = carDriverService.findMainDriverByCarId(carId);

        Date birthdate = mainCarDriver.getDriver().getBirthdate();
        Instant dateReference = budgetDto.getInsuranceEntity().getCreationDt();

        int age = calcAge(birthdate, dateReference);

        if(age >= 18 && age < 26) {
            budgetDto.increasePercentage(2);
        }

        log.info("Executou segunda regra");

        return super.handle(budgetDto);
    }

    private Integer calcAge(Date birthDate, Instant dateReference) {
        Instant birthDateInstant = birthDate.toInstant();

        if(isNull(dateReference)){
            dateReference = Instant.now(); //Todo corrigir isso
        }

        Long instantAge = dateReference.getEpochSecond() - birthDateInstant.getEpochSecond() ;

        if(instantAge > 0) {
            return Math.toIntExact(instantAge / 31536000L);
        }
        throw new RuntimeException(); //todo exceção de idade negativa
    }

}
