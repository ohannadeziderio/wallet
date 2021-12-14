package com.wallet.api.account.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.account.domain.AccountRequest;
import com.wallet.api.account.domain.AccountResponse;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponse save(AccountRequest accountRequest) {
        boolean existsAccountWithDocumentNumber = findAccountByDocumentNumber(accountRequest.getDocumentNumber()).isPresent();

        if(existsAccountWithDocumentNumber) {
            throw new EntityExistsException();
        }

        Account account = populateAccount(accountRequest);

        accountRepository.save(account);

        return new AccountResponse(account.getId(), account.getDocumentNumber());
    }

    private Optional<Account> findAccountByDocumentNumber(int documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber);
    }

    public AccountResponse getAccountById(int accountId) {
        Account account = findById(accountId);

        return populateAccountDTO(account);
    }

    public Account findById(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);

        return account;
    }

    private Account populateAccount(AccountRequest accountRequest) {
        Account account = new Account(accountRequest.getDocumentNumber());

        return account;
    }

    private AccountResponse populateAccountDTO(Account account) {
        AccountResponse accountResponse = new AccountResponse(account.getId(), account.getDocumentNumber());

        return accountResponse;
    }
}
