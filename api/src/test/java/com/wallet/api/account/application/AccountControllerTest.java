package com.wallet.api.account.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.api.account.domain.Account;
import com.wallet.api.account.domain.AccountDTO;
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
        AccountDTO accountDTO = new AccountDTO();

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldSuccessfullyAddAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setDocumentNumber(654321);

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDTO)))
            .andExpect(status().isOk());

        Account account = accountRepository.findById(1).orElse(null);

        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getDocumentNumber(), accountDTO.getDocumentNumber());
    }

}
