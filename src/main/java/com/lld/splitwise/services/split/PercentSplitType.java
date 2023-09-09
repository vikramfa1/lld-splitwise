package com.lld.splitwise.services.split;

import com.lld.splitwise.model.Split.Split;

import java.util.List;

public class PercentSplitType implements SplitService{
    @Override
    public boolean validateSplit(List<Split> splitList, double expenseAmount) {
        return true;
    }
}
