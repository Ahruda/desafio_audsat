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
public class DriverHasClaimsRule extends AbstractInsuranceBudgetPercentageHandle {

    private final ClaimService claimService;

    @Override
    public BudgetDto handle(BudgetDto budgetDto) {

        log.info("I=Iniciando_verificacao_da_regra_DriverHasClaimsRule");

        Long driverId = budgetDto.getInsuranceEntity().getCustomer().getDriver().getId();

        List<ClaimEntity> claimsDriverEntities = claimService.findByDriverId(driverId);

        if(!claimsDriverEntities.isEmpty()) { //todo testar caso venha null
            budgetDto.increasePercentage(2);
            log.info("I=Enquadrado_na_regra_DriverHasClaimsRule");
        }

        return super.handle(budgetDto);
    }

}
