package com.lld.splitwise;

import com.lld.splitwise.model.Split.Split;
import com.lld.splitwise.model.Split.SplitType;
import com.lld.splitwise.model.User;
import com.lld.splitwise.model.UserGroup;
import com.lld.splitwise.services.UserExpenseBalanceSheetService;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseApp {

    List<User> userList;
    List<UserGroup> userGroupList;

    UserExpenseBalanceSheetService userExpenseBalanceSheetService;
    private static SplitwiseApp splitwiseApp;
    private SplitwiseApp() {}

    private static SplitwiseApp getInstance() {
        if(splitwiseApp==null) {
            splitwiseApp = new SplitwiseApp();
            splitwiseApp.userList = new ArrayList<>();
            splitwiseApp.userGroupList = new ArrayList<>();
            splitwiseApp.userExpenseBalanceSheetService = new UserExpenseBalanceSheetService();
        }
        return splitwiseApp;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }
    public void addUserGroup(UserGroup group) {
        this.userGroupList.add(group);
    }

    public static void main(String args[]) throws Exception {
        setupUserAndGroup();
        SplitwiseApp splitwiseApp1 = SplitwiseApp.getInstance();

        User user1 = splitwiseApp1.userList.get(0);
        User user2 = splitwiseApp1.userList.get(1);
        User user3 = splitwiseApp1.userList.get(2);
        UserGroup userGroup = splitwiseApp1.userGroupList.get(0);

        List<Split> splitList = new ArrayList<>();
        splitList.add(new Split(user1, 300));
        splitList.add(new Split(user2, 300));
        splitList.add(new Split(user3, 300));
        userGroup.createExpense(user1, SplitType.EQUAL, splitList, "EXP1-BREAKFAST", 900);

        List<Split> splitList2 = new ArrayList<>();
        splitList2.add(new Split(user1, 200));
        splitList2.add(new Split(user2, 300));
        userGroup.createExpense(user2, SplitType.PERCENT, splitList2, "EXP2-LUNCH", 500);


        for(User user : splitwiseApp.userList) {
            splitwiseApp1.userExpenseBalanceSheetService.showUserBalanceSheet(user);
        }

    }

    public static void setupUserAndGroup() {
        SplitwiseApp splitwiseApp = SplitwiseApp.getInstance();
        User user1 = new User("vignesh");
        user1.setUserid("vignesh-12");
        user1.setPassword("@3e42");

        User user2 = new User("vignesh1");
        user2.setUserid("vignesh1-13");
        user2.setPassword("@3e42");

        User user3 = new User("vignesh2");
        user3.setUserid("vignesh2-14");
        user3.setPassword("@3e43");

        splitwiseApp.addUser(user1);
        splitwiseApp.addUser(user2);
        splitwiseApp.addUser(user3);

        UserGroup userGroup1 = new UserGroup("3242");
        userGroup1.addMember(user1);
        userGroup1.addMember(user2);
        userGroup1.addMember(user3);
        splitwiseApp.addUserGroup(userGroup1);
    }
}
