package com.ebanking.beneficiary.infrastructure;

import com.ebanking.beneficiary.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository
        extends JpaRepository<Beneficiary, Long> {
}
