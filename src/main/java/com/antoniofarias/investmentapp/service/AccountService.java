package com.antoniofarias.investmentapp.service;

import com.antoniofarias.investmentapp.client.BrapiClient;
import com.antoniofarias.investmentapp.dto.AccountResponseDto;
import com.antoniofarias.investmentapp.dto.AccountStockResponseDto;
import com.antoniofarias.investmentapp.dto.AssociateAccountStockDto;
import com.antoniofarias.investmentapp.entity.AccountStock;
import com.antoniofarias.investmentapp.entity.AccountStockId;
import com.antoniofarias.investmentapp.repository.AccountRepository;
import com.antoniofarias.investmentapp.repository.AccountStockRepository;
import com.antoniofarias.investmentapp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private AccountRepository accountRepository;

    private StockRepository stockRepository;

    private AccountStockRepository accountStockRepository;

    private BrapiClient brapiClient;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }

    public void associateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //DTO -> ENTITY
        var id = new AccountStockId(
                account.getAccountId(),
                stock.getStockId()
        );

        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity(),
                dto.data()
        );

        accountStockRepository.save(entity);

    }

    public List<AccountStockResponseDto> stockList(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStock()
                .stream()
                .map( as ->
                        new AccountStockResponseDto(
                                as.getStock().getStockId(),
                                as.getQuantity(),
                                getTotal(
                                        as.getQuantity(),
                                        as.getStock().getStockId())))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
