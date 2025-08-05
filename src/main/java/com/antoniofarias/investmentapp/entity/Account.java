package com.antoniofarias.investmentapp.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account")
    @Column(name = "list_stocks")
    private List<AccountStock> accountStock;

    public Account() {
    }

    public Account(String description, UUID accountId) {
        this.description = description;
        this.accountId = accountId;
    }

    public Account(UUID accountId, User user, String description, List<AccountStock> accountStock) {
        this.accountId = accountId;
        this.user = user;
        this.description = description;
        this.accountStock = accountStock;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
