package com.audsat.carinsurance.carInsurance.Controller;

import com.audsat.carinsurance.carInsurance.Request.InsuranceRequest;
import com.audsat.carinsurance.carInsurance.Response.InsuranceResponse;
import com.audsat.carinsurance.carInsurance.Service.InsuranceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("insurance/budget")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceResponse> findInsuranceById(@PathVariable @NotNull Long id) {

        log.info("I=Requisicao_recebida c=InsuranceController m=findInsuranceById, id={}", id);

        InsuranceResponse response = insuranceService.findInsuranceById(id);

        log.info("I=Requisicao_concluida c=InsuranceController m=findInsuranceById, r={}", response);

        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<InsuranceResponse> createInsurance(@RequestBody @Valid InsuranceRequest insuranceRequest,
                                                             UriComponentsBuilder uriBuilder) {

        log.info("I=Requisicao_recebida c=InsuranceController m=createInsurance, insuranceRequest={}", insuranceRequest);

        Long entityId = insuranceService.createInsurance(insuranceRequest);

        //Captura entidade atualizada depois que a transação foi comitada no banco
        InsuranceResponse entity = insuranceService.findInsuranceById(entityId);

        URI uri = uriBuilder.path("insurance/budget/{id}").buildAndExpand(entity.getId()).toUri();

        log.info("I=Requisicao_concluida c=InsuranceController m=createInsurance, insuranceRequest={}", entity);

        return ResponseEntity.created(uri).body(entity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceResponse> updateInsurance(@PathVariable @NotNull Long id,
                                                             @RequestBody @Valid InsuranceRequest insuranceRequest) {

        Long entityId = insuranceService.updateInsurance(id, insuranceRequest);

        //Captura entidade atualizada depois que a transação foi comitada no banco
        InsuranceResponse entity = insuranceService.findInsuranceById(entityId);

        log.info("I=Requisicao_concluida c=InsuranceController m=updateInsurance, insuranceRequest={}", entity);

        return ResponseEntity.ok(entity);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteInsurance(@PathVariable @NotNull Long id) {

        insuranceService.deleteInsurance(id);

        return ResponseEntity.ok().build();

    }
}