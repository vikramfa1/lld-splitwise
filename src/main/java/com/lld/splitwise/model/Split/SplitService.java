package com.lld.splitwise.model.Split;

import java.util.List;

public interface SplitService {

    public boolean validateSplit(List<Split> splitList, double expenseAmount);
}
