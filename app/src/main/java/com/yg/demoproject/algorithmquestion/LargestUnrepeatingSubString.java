package com.yg.demoproject.algorithmquestion;

import com.yg.demoproject.Utils;

import java.util.ArrayDeque;

public class LargestUnrepeatingSubString implements IQuestion {
    String mSource;
    LargestUnrepeatingSubString() {
        initData();
    }

    void initData() {
        mSource = Utils.randomCString(Utils.randomInt(0, 100));
    }

    @Override
    public String getTitle() {
        return "最长不重复子串";
    }

    @Override
    public String getQuestionDesc() {
        return "给定一个字符串，要求返回不包含重复字符的最长子串的长度";
    }

    @Override
    public String getTestData() {
        return mSource;
    }

    @Override
    public String refreshTestData() {
        initData();
        return getTestData();
    }

    @Override
    public String getResult() {
        char[] chars = mSource.toCharArray();
        ArrayDeque<Character> deque = new ArrayDeque<>();
        int maxLength = 0;
        deque.addLast(chars[0]);
        for (int i = 0; i < chars.length; i++) {
            if (deque.contains(chars[i])) {
                if (deque.size() > maxLength) {
                    maxLength = deque.size();
                }
                while (!deque.removeFirst().equals(chars[i]));
            }

            deque.addLast(chars[i]);
        }
        if (deque.size() > maxLength) {
            maxLength = deque.size();
        }
        return maxLength + "";
    }
}
