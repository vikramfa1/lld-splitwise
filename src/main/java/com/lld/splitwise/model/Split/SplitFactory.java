package com.lld.splitwise.model.Split;

public class SplitFactory {

    public static SplitService getSplitType(SplitType splitType) {
        SplitService splitService;
        switch (splitType) {
            case EQUAL:
                splitService = new EqualSplitType();
                break;
            case PERCENT:
                splitService = new PercentSplitType();
                break;
            default:
                splitService = null;
        }
        return splitService;
    }
}
