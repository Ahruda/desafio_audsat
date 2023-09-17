package com.audsat.carinsurance.carInsurance.BussinesRules.Rules;

import com.audsat.carinsurance.carInsurance.BussinesRules.AbstractInsuranceBudgetPercentageHandle;
import com.audsat.carinsurance.carInsurance.Dto.BudgetDto;
import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Service.ClaimService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CarHasClaimsRule extends AbstractInsuranceBudgetPercentageHandle {

    private final ClaimService claimService;

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        Long carId = budgetDto.getInsuranceEntity().getCar().getId();

        List<ClaimEntity> claimsCarEntities = claimService.findByCarId(carId);

        if(!claimsCarEntities.isEmpty()) { //todo testar caso venha null
            budgetDto.increasePercentage(2);
        }
        log.info("Executou ultima regra");
        return super.handle(budgetDto);
    }

}