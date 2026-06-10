package com.ebanking.beneficiary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ebanking.auth.domain.User;
import com.ebanking.shared.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "beneficiaries")
public class Beneficiary extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String beneficiaryName;

    @Column(nullable = false)
    private String beneficiaryAccountNumber;

    @Column(nullable = false)
    private String ifscCode;

    @Column(nullable = false)
    private String bankName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
