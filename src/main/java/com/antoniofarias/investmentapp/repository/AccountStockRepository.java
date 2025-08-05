package com.antoniofarias.investmentapp.repository;

import com.antoniofarias.investmentapp.entity.AccountStock;
import com.antoniofarias.investmentapp.entity.AccountStockId;
import com.antoniofarias.investmentapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
