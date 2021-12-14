package com.wallet.api.transaction.application;

import com.wallet.api.transaction.domain.TransactionDTO;
import com.wallet.api.transaction.infraestructure.TransactionService;
import io.swagger.annotations.Api;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Transactions")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO trasaction = transactionService.save(transactionDTO);

            return ResponseEntity.ok().body(trasaction);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The account does not exists", HttpStatus.NOT_FOUND);
        }
    }

}
