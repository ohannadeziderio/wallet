package com.wallet.api.transaction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequest {

    @NotNull(message = "Account cannot be null")
    private int accountId;

    @NotNull(message = "Operation type cannot be null")
    private int operationTypeId;

    private float amount;

    @JsonIgnore
    private Date eventDate;

    public TransactionRequest() {

    }

    public TransactionRequest(int accountId, int operationTypeId, float amount,
        Date eventDate) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
        this.eventDate = eventDate;
    }
}
