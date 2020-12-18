package com.yg.demoproject.algorithmquestion;

import com.yg.demoproject.Utils;

public class SignedIntReverse implements IQuestion {
    int mSource;
    int lable = 0;

    SignedIntReverse() {
        initData();
    }

    void initData() {
        int type = Utils.randomInt(0,6);
        if (type == 0) {
            mSource = Integer.MIN_VALUE;
            lable = -1;
        } else if (type == 1) {
            mSource = Integer.MAX_VALUE;
            lable = 1;
        } else {
            lable = 0;
            int s = Utils.randomInt(0,2);
            if (s == 0) s = -1;
            mSource = s * Utils.randomInt(0, Integer.MAX_VALUE);
        }
    }

    @Override
    public String getTitle() {
        return "整数反转";
    }

    @Override
    public String getQuestionDesc() {
        return "给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。\n" +
                "注意:\n" +
                "\n" +
                "假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。\n" +
                "\n示例 1:\n" +
                "\n" +
                "输入: 123\n" +
                "输出: 321\n" +
                " 示例 2:\n" +
                "\n" +
                "输入: -123\n" +
                "输出: -321\n" +
                "示例 3:\n" +
                "\n" +
                "输入: 120\n" +
                "输出: 21";
    }

    @Override
    public String getTestData() {
        if (lable == -1) {
            return mSource + "(min)";
        }else if (lable == 1) {
            return mSource + "(max)";
        } else {
            return mSource + "";
        }
    }

    @Override
    public String refreshTestData() {
        initData();
        return getTestData();
    }

    @Override
    public String getResult() {
        return getResult(mSource);
    }

    String getResult(int source) {
        int result = 0;
        int positive = 1;
        if (source < 0) positive = -1;
        while (true) {
            int tmp = source % 10;
            if (outOf(result << 1) || outOf(result << 2) || outOf(result << 3)) {
                result = 0;
                break;
            }
            result = result * 10 + tmp * positive;
            if (outOf(result)) {
                result = 0;
                break;
            }
            source /= 10;
            if (source == 0) break;
        }
        return (result * positive) +"";
    }

    boolean outOf(int s) {
        int lable = 1 << 31;
        if (((s ^ lable) & lable) == lable) {
            return false;
        }
        return true;
    }
}
