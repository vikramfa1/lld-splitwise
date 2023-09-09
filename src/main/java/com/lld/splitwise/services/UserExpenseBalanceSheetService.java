package com.lld.splitwise.services;

import com.lld.splitwise.model.Balance;
import com.lld.splitwise.model.Split.Split;
import com.lld.splitwise.model.User;
import com.lld.splitwise.model.UserExpenseBalanceSheet;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class UserExpenseBalanceSheetService {

    public void updateBalanceSheet(User expensePaidBy, List<Split> splitList, double totalExpAmt) {

        UserExpenseBalanceSheet userExpenseBalanceSheet = expensePaidBy.getUserExpenseBalanceSheet();
        userExpenseBalanceSheet.setTotalPayment(userExpenseBalanceSheet.getTotalPayment()+totalExpAmt);

        for(Split split: splitList) {
            User oweUser = split.getUser();
            UserExpenseBalanceSheet oweBalanceSheet = oweUser.getUserExpenseBalanceSheet();

            if(oweUser.getUserid().equals(expensePaidBy.getUserid())) {
                oweBalanceSheet.setTotalYourExpense(oweBalanceSheet.getTotalYourExpense() + split.getAmountOwe());
            } else {

                userExpenseBalanceSheet.setTotalAmountGetBack(userExpenseBalanceSheet.getTotalAmountGetBack()+split.getAmountOwe());
                Balance balanceObj;
                if(userExpenseBalanceSheet.getUserVsBalance().containsKey(oweUser.getUserid())) {
                    balanceObj = userExpenseBalanceSheet.getUserVsBalance().get(oweUser.getUserid());
                } else {
                    balanceObj = new Balance();
                    userExpenseBalanceSheet.getUserVsBalance().put(oweUser.getUserid(), balanceObj);
                }

                balanceObj.setAmountGetBack(balanceObj.getOwedAmount()+ split.getAmountOwe());

                oweBalanceSheet.setTotalAmountOwe(oweBalanceSheet.getTotalAmountOwe()+ split.getAmountOwe());
                oweBalanceSheet.setTotalYourExpense(oweBalanceSheet.getTotalYourExpense()+ split.getAmountOwe());

                Balance balanceObj1;
                if(oweBalanceSheet.getUserVsBalance().containsKey(expensePaidBy.getUserid())) {
                    balanceObj1 = oweBalanceSheet.getUserVsBalance().get(expensePaidBy.getUserid());
                } else {
                    balanceObj1 = new Balance();
                    oweBalanceSheet.getUserVsBalance().put(expensePaidBy.getUserid(), balanceObj1);
                }

                balanceObj1.setOwedAmount(balanceObj1.getOwedAmount()+split.getAmountOwe());

            }
        }
    }

    public void updateBalanceSheetToReverseExpense(User expensePaidBy, List<Split> splitList, double totalExpAmt) {

    }

    public void showUserBalanceSheet (User user) {
        UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet();
        log.info("paid user: {}, name: {} ", user.getUserid(), user.getName());
        log.info("--------------------------------------");
        log.info("total amount paid, {}" , userExpenseBalanceSheet.getTotalPayment());
        log.info("total amount getback, {}" , userExpenseBalanceSheet.getTotalAmountGetBack());
        log.info("total amount owe, {}" , userExpenseBalanceSheet.getTotalAmountOwe());
        log.info("total expense spent, {}" , userExpenseBalanceSheet.getTotalYourExpense());

        for(Map.Entry<String, Balance> entryBalance: userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
            log.info("userId: {}, amountOwedTo:{} , amountGetBack: {}", entryBalance.getKey(), entryBalance.getValue().getOwedAmount(), entryBalance.getValue().getAmountGetBack() );
        }

        log.info("--------------------------------------");
    }

}
