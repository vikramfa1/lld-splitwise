package com.lld.splitwise.model.Split;

import java.util.List;

public class PercentSplitType implements SplitService{
    @Override
    public boolean validateSplit(List<Split> splitList, double expenseAmount) {
        return true;
    }
}
