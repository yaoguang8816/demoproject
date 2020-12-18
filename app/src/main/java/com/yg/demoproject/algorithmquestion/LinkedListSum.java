package com.yg.demoproject.algorithmquestion;


import com.yg.demoproject.Utils;

import java.util.LinkedList;
import java.util.List;

public class LinkedListSum implements IQuestion {
    LinkedList<Integer> mFirstList = new LinkedList<>();
    LinkedList<Integer> mSecondList = new LinkedList<>();

    LinkedListSum() {
        initData();
    }

    void initData() {
        mFirstList.clear();
        mSecondList.clear();
        int firstLength = Utils.randomInt(0,32);
        int secondLength = Utils.randomInt(0,32);
        for (int i = 0; i < firstLength; i++) {
            mFirstList.add(Utils.randomInt(0, 9));
        }
        for (int i = 0; i < secondLength; i++) {
            mSecondList.add(Utils.randomInt(0, 9));
        }
    }
    @Override
    public String getTitle() {
        return "用链表模拟加法";
    }

    @Override
    public String getQuestionDesc() {
        return "给定两个非空链表，代表两个非负整数。这两个数都是倒叙存储，要求返回一个链表，表示这两个数的和。";
    }

    @Override
    public String getTestData() {
        return listToString(mFirstList) + "\n" + listToString(mSecondList);
    }

    @Override
    public String refreshTestData() {
        initData();
        return getTestData();
    }

    @Override
    public String getResult() {
        boolean advance = false;
        if (mFirstList.size() < mSecondList.size()) {
            LinkedList targetList = mFirstList;
            mFirstList = mSecondList;
            mSecondList = targetList;
        }
        for (int i = 0; i < mFirstList.size(); i++) {
            int tmp;
            if (i < mSecondList.size()) {
                tmp = mFirstList.get(i) + mSecondList.get(i);
            } else {
                tmp = mFirstList.get(i);
            }
            if (advance) {
                tmp++;
                advance = false;
            }
            if (tmp > 9) {
                advance = true;
            }
            mFirstList.set(i, tmp%10);
        }

        return listToString(mFirstList);
    }

    String listToString(List list) {
        StringBuilder result = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.append(list.get(i));
        }
        return result.toString();
    }
}
