package com.antoniofarias.investmentapp.controller;

import com.antoniofarias.investmentapp.dto.AccountStockResponseDto;
import com.antoniofarias.investmentapp.dto.AssociateAccountStockDto;
import com.antoniofarias.investmentapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                               @RequestBody AssociateAccountStockDto dto){

        accountService.associateStock(accountId, dto);

        return ResponseEntity.ok().build();

    }

    @GetMapping("{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> listStocks(@PathVariable("accountId") String accountId){
        var stocks = accountService.stockList(accountId);

        return ResponseEntity.ok(stocks);
    }
}
