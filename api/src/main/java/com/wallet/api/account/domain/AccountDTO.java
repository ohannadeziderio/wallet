package com.wallet.api.account.domain;

import java.math.BigInteger;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {

    private int id;

    @Min(1)
    @NotNull(message = "Document number cannot be null")
    private BigInteger documentNumber;

    public AccountDTO(){
    }

    public AccountDTO(int id){
        this.id = id;
    }

}
