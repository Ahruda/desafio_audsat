package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import com.audsat.carinsurance.carInsurance.Entity.DriverEntity;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Mapper.InsuranceMapper;
import com.audsat.carinsurance.carInsurance.Repository.CarDriverRepository;
import com.audsat.carinsurance.carInsurance.Repository.ClaimRepository;
import com.audsat.carinsurance.carInsurance.Repository.InsuranceRepository;
import com.audsat.carinsurance.carInsurance.Request.InsuranceRequest;
import com.audsat.carinsurance.carInsurance.Response.BudgetResponse;
import com.audsat.carinsurance.carInsurance.Response.InsuranceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;

    private final CustomerService customerService;
    private final CarService carService;

    private final BudgetService budgetService;

    public InsuranceResponse findInsuranceById(Long id) {

        log.info("I=Buscando_insurance_no_banco c=InsuranceService m=findInsuranceById, id={}", id);

        InsuranceEntity entity = insuranceRepository.getReferenceById(id);

        log.info("I=Retorno_do_banco c=InsuranceService m=findInsuranceById, InsuranceEntity={}", entity);
         //todo regras de negocio nesse ponto

        BudgetResponse budget = budgetService.calculateInsurance(entity);

        return insuranceMapper.toResponse(entity, budget);
    }

    @Transactional
    public InsuranceResponse createInsurance(InsuranceRequest insuranceRequest) {

        log.info("I=Requisitando_customer_car c=InsuranceService m=createInsurance, " +
                "InsuranceRequest={}", insuranceRequest);

        CustomerEntity customer = customerService.getCustomerById(insuranceRequest.getCustomerId());

        CarEntity car = carService.getCarById(insuranceRequest.getCarId());

        log.info("I=Resposta_service c=InsuranceService m=createInsurance, " +
                "CustomerEntity={} CarEntity={}", customer, car);

        InsuranceEntity entity = insuranceMapper.toEntity(insuranceRequest, customer, car);

        log.info("I=Retorno_mapper c=InsuranceService m=createInsurance, InsuranceEntity={}", entity);

        entity = insuranceRepository.save(entity);

        log.info("I=Insurance_cadastrado_com_sucesso c=InsuranceService m=createInsurance, InsuranceEntity={} ", entity);

        BudgetResponse budget = budgetService.calculateInsurance(entity);

        //todo regras de negocio nesse ponto
        return insuranceMapper.toResponse(entity, budget);

    }

    @Transactional
    public InsuranceResponse updateInsurance(Long id, InsuranceRequest insuranceRequest) {

        log.info("I=Requisitando_entity_customer_car c=InsuranceService m=updateInsurance, " +
                "InsuranceRequest={}", insuranceRequest);

        InsuranceEntity entity = insuranceRepository.getReferenceById(id);
        CustomerEntity customer = customerService.getCustomerById(insuranceRequest.getCustomerId());
        CarEntity car = carService.getCarById(insuranceRequest.getCarId());

        log.info("I=Resposta_service c=InsuranceService m=updateInsurance, " +
                "InsuranceEntity={} CustomerEntity={} CarEntity={}", entity, customer, car);

        insuranceMapper.toUpdateEntity(insuranceRequest, customer, car, entity);

        log.info("I=Entidade_atualizada c=InsuranceService m=updateInsurance, InsuranceEntity={} ", entity);

        BudgetResponse budget = budgetService.calculateInsurance(entity);

        return insuranceMapper.toResponse(entity, budget);

    }

    @Transactional
    public void deleteInsurance(Long id) {

        insuranceRepository.deleteById(id);

        log.info("I=Registro_excluido c=InsuranceService m=deleteInsurance, Id={} ", id);

    }

}