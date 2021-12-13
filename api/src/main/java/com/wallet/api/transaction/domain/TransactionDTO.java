package com.wallet.api.transaction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.api.account.domain.AccountDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
@Data
public class TransactionDTO {

    @JsonIgnore
    private int id;

    @NotNull(message = "Account cannot be null")
    private int accountId;

    @NotNull(message = "Operation type cannot be null")
    private int operationTypeId;

    private float amount;

    @JsonIgnore
    private Date eventDate;

    public TransactionDTO() {
    }

    public TransactionDTO(int id) {
        this.id = id;
    }

}
