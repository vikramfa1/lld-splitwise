package com.lld.splitwise.model;

import lombok.Data;

@Data
public class User extends Account{
    private String name;
    private UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String name) {
        this.name = name;
        this.userExpenseBalanceSheet = new UserExpenseBalanceSheet();
    }
}
