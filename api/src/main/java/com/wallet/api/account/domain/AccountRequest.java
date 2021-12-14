package com.wallet.api.account.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {

    @Min(1)
    @NotNull(message = "Document number cannot be null")
    private int documentNumber;

    public AccountRequest() {

    }
    
    public AccountRequest(int documentNumber) {
        this.documentNumber = documentNumber;
    }
}
