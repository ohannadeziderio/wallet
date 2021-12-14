package com.wallet.api.account.application;

import com.wallet.api.account.domain.AccountRequest;
import com.wallet.api.account.domain.AccountResponse;
import com.wallet.api.account.infraestructure.AccountService;
import io.swagger.annotations.Api;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Accounts")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody AccountRequest accountRequest) {
        try {
            AccountResponse account = accountService.save(accountRequest);

            return ResponseEntity.ok().body(account);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("The account exists", HttpStatus.FOUND);
        }

    }

    @GetMapping("/{accountId}")
    public ResponseEntity getById(@PathVariable("accountId") String accountId) {
        try{
            AccountResponse account = accountService.getAccountById(Integer.parseInt(accountId));

            return ResponseEntity.ok().body(account);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The account does not exists", HttpStatus.NOT_FOUND);
        }
    }

}
