package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.Entity.CarDriverEntity;
import com.audsat.carinsurance.carInsurance.Repository.CarDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarDriverService {

    private final CarDriverRepository carDriverRepository;

    public CarDriverEntity findMainDriverByCarId(Long carId) {

        return carDriverRepository.findByCarIdAndIsMainDriver(carId, Boolean.TRUE);
    }

}
