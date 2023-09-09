package com.lld.splitwise.services;

import com.lld.splitwise.model.Expense;
import com.lld.splitwise.model.Split.Split;
import com.lld.splitwise.model.Split.SplitFactory;
import com.lld.splitwise.model.Split.SplitService;
import com.lld.splitwise.model.Split.SplitType;
import com.lld.splitwise.model.User;

import java.util.List;

public class ExpenseService {

    UserExpenseBalanceSheetService userExpenseBalanceSheetService;

    public ExpenseService() {
        userExpenseBalanceSheetService = new UserExpenseBalanceSheetService();
    }
    public Expense createExpense (User paidBy, SplitType splitType, List<Split> splitList,
                                  String desc, double expenseAmt) throws Exception {
        SplitService splitService = SplitFactory.getSplitType(splitType);
        if(!splitService.validateSplit(splitList, expenseAmt)) {
            throw new Exception("not a valid split");
        }

        Expense expense = new Expense();
        expense.setExpenseAmount(expenseAmt);
        expense.setDesc(desc);
        expense.setPaidBy(paidBy);
        expense.setSplit(splitList);
        expense.setSplitType(splitType);

        userExpenseBalanceSheetService.updateBalanceSheet(paidBy, splitList, expenseAmt);
        return expense;
    }

    public void deleteExpense(Expense expense) {
        userExpenseBalanceSheetService.updateBalanceSheetToReverseExpense(expense.paidBy, expense.getSplit(), expense.expenseAmount);
    }
}
