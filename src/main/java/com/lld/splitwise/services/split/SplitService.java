package com.lld.splitwise.services.split;

import com.lld.splitwise.model.Split.Split;

import java.util.List;

public interface SplitService {

    public boolean validateSplit(List<Split> splitList, double expenseAmount);
}
