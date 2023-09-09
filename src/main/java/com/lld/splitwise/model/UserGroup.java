package com.lld.splitwise.model;

import com.lld.splitwise.model.Split.Split;
import com.lld.splitwise.model.Split.SplitType;
import com.lld.splitwise.services.ExpenseService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserGroup {

    String groupId;
    List<User> userList;
    List<Expense> expensesList;
    ExpenseService expenseService;

    public UserGroup(String groupId) {
        this.groupId = groupId;
        this.userList = new ArrayList<>();
        this.expensesList = new ArrayList<>();
        this.expenseService = new ExpenseService();
    }

    public void addMember(User user) {
        this.userList.add(user);
    }

    public void createExpense(User paidBy, SplitType splitType, List<Split> splitList,
                              String desc, double expenseAmt) throws Exception {
        Expense expense = expenseService.createExpense(paidBy, splitType, splitList, desc, expenseAmt);
        expensesList.add(expense);
    }

    public void deleteExpense(Expense expense) throws Exception {
        expenseService.deleteExpense(expense);
        expensesList.remove(expense);
    }
}
