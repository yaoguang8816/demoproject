package com.yg.demoproject.algorithmquestion;

import com.yg.demoproject.Utils;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayInterator implements IQuestion {
    int[] mSource;
    int mTarget;

    ArrayInterator() {
        initData();
    }

    void initData() {
        int num = Utils.randomInt(0,12);
        mSource = new int[num];
        for (int i = 0; i < num; i++) {
            mSource[i] = Utils.randomInt(0, num);
        }
        mTarget = Utils.randomInt(0, 2 * num + 1);
    }

    @Override
    public String getTitle() {
        return "数组遍历";
    }

    @Override
    public String getQuestionDesc() {
        return "给定一个全是int的数组和一个整数target，要求返回两个下标，使得数组当中这两个下标对应的和等于target。\n 你可以假设一定存在一个答案，并且一个元素不能使用两次。";
    }

    @Override
    public String getTestData() {
        return Arrays.toString(mSource) + "," + mTarget;
    }

    @Override
    public String refreshTestData() {
        initData();
        return getTestData();
    }

    @Override
    public String getResult() {
        String s = interatorResult(mSource, mTarget);
        if (s == null || s.length() < 2) s = "未找到";
        return s;
    }

    private String interatorResult(int[] source, int target) {
        if (source.length < 2) return null;
        StringBuilder result = new StringBuilder();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(source[0], 0);
        for (int i = 1; i < source.length; i++) {
            int d = target - source[i];
            if (hashMap.get(d) != null) {
                result.append(hashMap.get(d));
                result.append(",");
                result.append(i);
                break;
            } else {
                hashMap.put(source[i], i);
            }
        }

        return result.toString();
    }
}
