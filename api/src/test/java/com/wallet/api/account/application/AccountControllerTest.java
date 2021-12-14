package com.wallet.api.account.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.api.account.domain.Account;
import com.wallet.api.account.domain.AccountRequest;
import com.wallet.api.account.infraestructure.AccountRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Flyway flyway;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldReturn400WhenAddingAnInvalidAccount() throws Exception {
        AccountRequest accountRequest = new AccountRequest();

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldSuccessfullyAddAccount() throws Exception {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setDocumentNumber(654321);

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
            .andExpect(status().isOk());

        Account account = accountRepository.findById(1).orElse(null);

        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getDocumentNumber(), accountRequest.getDocumentNumber());
    }

    @Test
    public void shouldReturn404WhenTryingToGetANonExistentAccount() throws Exception {

        mockMvc.perform(get("/api/accounts/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("")))
            .andExpect(status().isNotFound());
    }

}
