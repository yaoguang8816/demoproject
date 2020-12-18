package com.yg.demoproject.algorithmquestion;

public class QuestionUtils {
    public static final String EXTRA_QUESTION_TYPE = "question_type";
    public static final String EXTRA_BUNDLE = "target_bundle";


    public static final int ARRAY_INTERATOR = 1;
    public static final int LINKEDLIST_SUM = 2;
    public static final int LARGEST_UNREPEATING_SUB_STRING = 3;
    public static final int MEDIAN_NUMBER = 4;
    public static final int SIGNED_INT_REVERSE = 5;

    static IQuestion getInstance(int type) {
        switch (type) {
            case ARRAY_INTERATOR:
                return new ArrayInterator();
            case LINKEDLIST_SUM:
                return new LinkedListSum();
            case LARGEST_UNREPEATING_SUB_STRING:
                return new LargestUnrepeatingSubString();
            case MEDIAN_NUMBER:
                return new MedianNumber();
            case SIGNED_INT_REVERSE:
                return new SignedIntReverse();
        }
        return null;
    }
}
