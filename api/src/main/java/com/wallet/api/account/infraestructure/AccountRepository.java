package com.wallet.api.account.infraestructure;

import com.wallet.api.account.domain.Account;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findByDocumentNumber(BigInteger documentNumber);
}
