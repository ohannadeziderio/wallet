package com.wallet.api.transaction.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.account.infraestructure.AccountService;
import com.wallet.api.transaction.domain.OperationType;
import com.wallet.api.transaction.domain.Transaction;
import com.wallet.api.transaction.domain.TransactionDTO;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final int PAGAMENTO = 4;

    TransactionRepository transactionRepository;
    AccountService accountService;
    OperationTypeService operationTypeService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, OperationTypeService operationTypeService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }

    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) {

        Transaction transaction = populateTransaction(transactionDTO);

        transactionRepository.save(transaction);

        return new TransactionDTO(transaction.getId());
    }

    private Transaction populateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();

        Account account = accountService.findById(transactionDTO.getAccountId());

        transaction.setAccount(account);

        OperationType operationType = operationTypeService.findById(transactionDTO.getOperationTypeId());

        transaction.setOperationType(operationType);
        transaction.setAmount(transactionDTO.getAmount());

        if(transaction.getOperationType().getId() != PAGAMENTO) {
            transaction.setAmount(-transactionDTO.getAmount());
        }

        return transaction;
    }
}
