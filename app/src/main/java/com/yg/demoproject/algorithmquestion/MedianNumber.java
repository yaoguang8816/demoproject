package com.yg.demoproject.algorithmquestion;

import com.yg.demoproject.Utils;

import java.util.Arrays;

/**
 * 中位数：指的是一个有序数组中居于中间的那个数字，如果数组大小是偶数，则中位数表示的是中间的两个数字和的一半
 * 例如：A: 1 3 5 7 9， Median = 5
 *      B: 1 2 3 4 5 6, Median = (3+4)/2 = 3.5
 *
 *  解题思路：
 *  方法1：合并求解
 *  方法2：遍历，从A和B的最小值开始遍历
 *  方法3：找第K小的数，其中k为AB总数的一半去下整
 *  如上数组： A和B长度为11，那么就找第11/2=5小的数字，即k为5
 *  要找到第k小的数，那么首先分别找到A和B中第k/2=2的数即A为3，B为2，
 *  因为A>B而且我们查找的A和B加起来总个数小于等于k，所以B对应的2肯定比第k小的数要小。
 *  那么接下来我们就可以去掉B的前（k/2）个数，即B的1和2
 *  这时候我们就可以在数组”1 3 5 7 9'和数组’3 4 5 6‘ 中继续查找k-k/2小的数字了，也就是第3小的数字
 *  因为3/2=1，所以分别找A和B的第1小数字，可以看到A的是1，B的是3，那么去掉A的1，这时候剩余数组”3 5 7 9'和数组’3 4 5 6‘
 *  接下来查找k-2-1=2,因为2/2=1，所以继续找A和B的第1小数字，可以看到都是3，所以随便去一个，比如我们去掉A的，那么这时候剩余数组”5 7 9'和数组’3 4 5 6‘
 *  这时候下一个就是第k小了，对比AB可以知道是B的3，也就是AB的第k小数字是3
 */
public class MedianNumber implements IQuestion {
    int [] mFirst;
    int [] mSecond;
    MedianNumber() {
        initData();
    }

    void initData() {
        int firstLength = Utils.randomInt(0, 18);
        int secondLength = Utils.randomInt(0, 18);
        mFirst = new int[firstLength];
        mSecond = new int[secondLength];

        for (int i = 0; i < firstLength; i++) {
            int tmp = Utils.randomInt(0, 10);
            if (i == 0) {
                mFirst[i] = tmp;
            } else {
                mFirst[i] += mFirst[i - 1] + tmp;
            }
        }
        for (int i = 0; i < secondLength; i++) {
            int tmp = Utils.randomInt(0, 10);
            if (i == 0) {
                mSecond[i] = tmp;
            } else {
                mSecond[i] += mSecond[i - 1] + tmp;
            }
        }
    }

    @Override
    public String getTitle() {
        return "寻找两个正序数组的中位数";
    }

    @Override
    public String getQuestionDesc() {
        return "给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。\n" +
                "\n" +
                "进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？\n";
    }

    @Override
    public String getTestData() {
        return "nums1[] = " + Arrays.toString(mFirst) + "\n" + "nums2[] = " + Arrays.toString(mSecond);
    }

    @Override
    public String refreshTestData() {
        initData();
        return getTestData();
    }

    @Override
    public String getResult() {
        return getMedian() + "";
    }

    float getMedian() {
        int totalLength = mFirst.length + mSecond.length;
        //对于奇数序列，left和right相同，偶数序列则是相邻的两个数
        int left = (totalLength + 1) / 2;
        int right = (totalLength + 2) / 2;
        //(left + right)/2 为中位数
        float result = (findK(mFirst, 0, mSecond, 0, left) + findK(mFirst, 0, mSecond, 0, right)) / 2;
        return result;
    }

    float findK(int[] first, int start, int[] second, int start2, int k) {
        if (start == first.length) {
            return second[start2 + k - 1];
        }
        if (start2 == second.length) {
            return first[start + k - 1];
        }
        if (k == 1) {
            return first[start] < second[start2] ? first[start] : second[start2];
        }
        //这两个变量分别代表两个序列的k2
        int firstKValue;
        int secondKValue;
        int k2 = k/2;
        //step 用来记录本次运行可以找出来的小于中位数的个数，正常情况应该就是k2，但是如果遇到某个序列剩余数小于k2，则需要记录。
        int step;
        int step1 = k2;
        int step2 = k2;
        //分别找出两个序列的k2
        if (first.length - start < k2){
            step1 = first.length - start;
            firstKValue = first[first.length -1];
        } else  {
            firstKValue = first[start + k2 - 1];
        }
        if (second.length - start2 < k2) {
            step2 = second.length - start2;
            secondKValue = second[second.length - 1];
        } else  {
            secondKValue = second[start2 + k2 - 1];
        }
        //比较k2，较小的移动start，要么移动k2步，要么移动到序列末尾
        if (firstKValue < secondKValue) {
            step = step1;
            start = Math.min(start + k2, first.length);
        } else {
            step = step2;
            start2 = Math.min(start2 + k2, second.length);
        }
        return findK(first, start, second, start2, k - step);
    }
}
