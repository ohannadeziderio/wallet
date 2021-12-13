package com.wallet.api.account.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.account.domain.AccountDTO;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDTO save(AccountDTO accountDTO) {


        Account account = populateAccount(accountDTO);

        accountRepository.save(account);

        return new AccountDTO(account.getId());
    }

    private Account populateAccount(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.getDocumentNumber());

        return account;
    }
}
