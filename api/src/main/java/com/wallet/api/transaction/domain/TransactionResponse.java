package com.wallet.api.transaction.domain;

import lombok.Data;

@Data
public class TransactionResponse {

    private int id;

    public TransactionResponse() {

    }

    public TransactionResponse(int id) {
        this.id = id;
    }
}
