package com.wallet.api.account.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name = "account_generator", sequenceName = "account_seq", allocationSize = 1)
    private int id;

    @Min(1)
    @NotNull(message = "Document number cannot be null")
    private BigInteger documentNumber;

    public Account(){
    }

    public Account(BigInteger documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
