package com.wallet.api.transaction.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.account.infraestructure.AccountService;
import com.wallet.api.transaction.domain.OperationType;
import com.wallet.api.transaction.domain.Transaction;
import com.wallet.api.transaction.domain.TransactionRequest;
import com.wallet.api.transaction.domain.TransactionResponse;
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
    public TransactionResponse save(TransactionRequest transactionRequest) {

        Transaction transaction = populateTransaction(transactionRequest);

        transactionRepository.save(transaction);

        return new TransactionResponse(transaction.getId());
    }

    private Transaction populateTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();

        Account account = accountService.findById(transactionRequest.getAccountId());

        transaction.setAccount(account);

        OperationType operationType = operationTypeService.findById(transactionRequest.getOperationTypeId());

        transaction.setOperationType(operationType);
        transaction.setAmount(transactionRequest.getAmount());

        if(transaction.getOperationType().getId() != PAGAMENTO) {
            transaction.setAmount(-transactionRequest.getAmount());
        }

        return transaction;
    }
}
