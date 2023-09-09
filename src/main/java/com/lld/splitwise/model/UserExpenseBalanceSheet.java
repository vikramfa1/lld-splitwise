package com.lld.splitwise.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserExpenseBalanceSheet {

    double totalAmountOwe;
    double totalAmountGetBack;
    double totalYourExpense;
    double totalPayment;
    Map<String, Balance> userVsBalance = new HashMap<>();


}
