package com.ebanking.transaction.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ebanking.account.domain.Account;
import com.ebanking.shared.domain.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @ManyToOne
	@JoinColumn(name = "from_account_id")
	@JsonIgnore
	private Account fromAccount;

	@ManyToOne
	@JoinColumn(name = "to_account_id")
	@JsonIgnore
	private Account toAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String transactionType;

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
