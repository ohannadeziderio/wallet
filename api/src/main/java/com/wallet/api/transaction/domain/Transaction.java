package com.wallet.api.transaction.domain;

import com.wallet.api.account.domain.Account;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(name = "transaction_generator", sequenceName = "transaction_seq", allocationSize = 1)
    private int id;

    @NotNull(message = "Account cannot be null")
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull(message = "Operation type cannot be null")
    @ManyToOne
    @JoinColumn(name = "operation_type_id")
    private OperationType operationType;

    private float amount;

    @CreatedDate
    @Timestamp
    @Column(name = "event_date")
    private Date eventDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id == transaction.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
