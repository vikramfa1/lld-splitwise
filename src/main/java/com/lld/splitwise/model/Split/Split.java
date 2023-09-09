package com.lld.splitwise.model.Split;

import com.lld.splitwise.model.User;
import lombok.Data;

@Data
public class Split {
    User user;
    double amountOwe;

    public Split() {}
    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
    }
}
