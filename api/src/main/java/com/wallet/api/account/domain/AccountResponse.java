package com.wallet.api.account.domain;

import lombok.Data;

@Data
public class AccountResponse {

    private int id;

    private int documentNumber;

    public AccountResponse() {

    }

    public AccountResponse(int id, int documentNumber) {
        this.id = id;
        this.documentNumber = documentNumber;
    }
}
