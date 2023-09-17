package com.audsat.carinsurance.carInsurance.Service;

import com.audsat.carinsurance.carInsurance.Entity.ClaimEntity;
import com.audsat.carinsurance.carInsurance.Repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimEntity getClaimById(Long claimId) {

       return claimRepository.getReferenceById(claimId);

    }

    public List<ClaimEntity> findByDriverId(Long driverId) {
        return claimRepository.findByDriverId(driverId);
    }

    public List<ClaimEntity> findByCarId(Long carId) {
        return claimRepository.findByCarId(carId);
    }
}
