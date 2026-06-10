package com.ebanking.beneficiary.controller;

import com.ebanking.auth.domain.User;
import com.ebanking.auth.infrastructure.UserRepository;
import com.ebanking.beneficiary.domain.Beneficiary;
import com.ebanking.beneficiary.infrastructure.BeneficiaryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    public BeneficiaryController(
            BeneficiaryRepository beneficiaryRepository,
            UserRepository userRepository
    ) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Beneficiary addBeneficiary(
            @RequestParam Long userId,
            @RequestBody Beneficiary beneficiary
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        beneficiary.setUser(user);

        return beneficiaryRepository.save(beneficiary);
    }

    @GetMapping
    public java.util.List<Beneficiary> getAll() {
        return beneficiaryRepository.findAll();
    }
}
