package com.lld.splitwise.model.Split;

import java.util.List;

public class EqualSplitType implements SplitService{

    @Override
    public boolean validateSplit(List<Split> splitList, double expenseAmt) {
        int cnt = splitList.size();
        double splitAmt = expenseAmt/cnt;
        boolean res = true;
        for(Split split: splitList) {
            if(split.amountOwe != splitAmt) {
                return false;
            }
        }
        return res;
    }

}
