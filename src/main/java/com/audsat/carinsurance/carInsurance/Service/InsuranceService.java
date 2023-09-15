package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.Mapper.InsuranceMapper;
import com.audsat.carinsurance.carInsurance.Repository.InsuranceRepository;
import com.audsat.carinsurance.carInsurance.Request.NewInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;

    public void newInsurance(NewInsuranceRequest newInsuranceRequest) {

    }
}
