package com.lld.splitwise.model;

import com.lld.splitwise.model.Split.Split;
import com.lld.splitwise.model.Split.SplitType;
import lombok.Data;

import java.util.List;

@Data
public class Expense {

    public User paidBy;
    public double expenseAmount;
    public List<Split> split;
    public SplitType splitType;
    public String desc;

}
