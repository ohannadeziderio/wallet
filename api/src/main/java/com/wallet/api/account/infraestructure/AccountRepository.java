package com.wallet.api.account.infraestructure;

import com.wallet.api.account.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
