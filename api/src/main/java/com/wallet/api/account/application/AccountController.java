package com.wallet.api.account.application;

import com.wallet.api.account.domain.AccountDTO;
import com.wallet.api.account.infraestructure.AccountService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity add(@Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO account = accountService.save(accountDTO);

        return ResponseEntity.ok().body(account);
    }

}
