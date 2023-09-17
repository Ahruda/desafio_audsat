package com.audsat.carinsurance.carInsurance.Mapper;

import com.audsat.carinsurance.carInsurance.Entity.CarEntity;
import com.audsat.carinsurance.carInsurance.Entity.CustomerEntity;
import com.audsat.carinsurance.carInsurance.Entity.InsuranceEntity;
import com.audsat.carinsurance.carInsurance.Request.InsuranceRequest;
import com.audsat.carinsurance.carInsurance.Response.BudgetResponse;
import com.audsat.carinsurance.carInsurance.Response.InsuranceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface InsuranceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InsuranceEntity toEntity(InsuranceRequest insuranceRequest, CustomerEntity customer, CarEntity car);

    InsuranceResponse toResponse(InsuranceEntity entity, BudgetResponse budget);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void toUpdateEntity(InsuranceRequest insuranceRequest, CustomerEntity customer, CarEntity car,
                        @MappingTarget InsuranceEntity entity);
}
