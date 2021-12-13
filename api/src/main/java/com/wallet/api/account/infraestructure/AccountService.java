package com.wallet.api.account.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.account.domain.AccountDTO;
import java.math.BigInteger;
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
    public AccountDTO save(AccountDTO accountDTO) {
        boolean existsAccountWithDocumentNumber = findAccountByDocumentNumber(accountDTO.getDocumentNumber()).isPresent();

        if(existsAccountWithDocumentNumber) {
            throw new EntityExistsException();
        }

        Account account = populateAccount(accountDTO);

        accountRepository.save(account);

        return new AccountDTO(account.getId());
    }

    private Optional<Account> findAccountByDocumentNumber(BigInteger documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber);
    }

    public AccountDTO findAccountById(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);

        return populateAccountDTO(account);
    }

    private Account populateAccount(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.getDocumentNumber());

        return account;
    }

    private AccountDTO populateAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getDocumentNumber());

        return accountDTO;
    }
}
