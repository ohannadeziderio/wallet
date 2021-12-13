package com.wallet.api.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigInteger;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {

    @JsonIgnore
    private int id;

    @Min(1)
    @NotNull(message = "Document number cannot be null")
    private BigInteger documentNumber;

    public AccountDTO(){
    }

    public AccountDTO(int id){
        this.id = id;
    }

    public AccountDTO(int id, BigInteger documentNumber){
        this.id = id;
        this.documentNumber = documentNumber;
    }

}
