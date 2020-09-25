package com.yg.demoproject;

import android.util.Log;

public class ThreadLocalTest {
    MyThreadLocal mMyS;

    void init () {
        mMyS = new MyThreadLocal();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    mMyS.set("Local1", 1, "NotLocal1");
                    mMyS.print();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    mMyS.set("Local2", 2, "NotLocal2");
                    mMyS.print();
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    class MyThreadLocal {
        ThreadLocal<String> sThreadLocalA = new ThreadLocal<>();
        ThreadLocal<Integer> sThreadLocalB = new ThreadLocal<>();
        String mNotLocalString;

        MyThreadLocal(){}

        void set(String s, Integer i, String s2) {
            mNotLocalString = s2;
            sThreadLocalA.set(s);
            sThreadLocalB.set(i);
        }

        void print() {
            Log.d("ThreadLocalTest", "result: " + sThreadLocalA.get() + ", " + sThreadLocalB.get() + ", " + mNotLocalString);
        }
    }
}
