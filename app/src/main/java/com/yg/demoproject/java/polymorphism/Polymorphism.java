package com.yg.demoproject.java.polymorphism;

/**
 * 继承
 * 重写
 * 基类引用指向派生类对象（引用还是指向基类）
 */
public class Polymorphism {

    void test() {
            Wine a = new JNC();
            a.fun1();
            //a.fun1("aaa"); //这个引用是错误的.
    }

    public class Wine {
        public void fun1() {
            System.out.println("Wine 的Fun.....");
            fun2();
        }

        public void fun2() {
            System.out.println("Wine 的Fun2...");
        }
    }

    public class JNC extends Wine {
        /**
         * @param a
         * @return void
         * @desc 子类重载父类方法
         * 父类中不存在该方法，向上转型后，父类是不能引用该方法的(注意与父类参数不同)
         */
        public void fun1(String a) {
            System.out.println("JNC 的 Fun1...");
            fun2();
        }

        /**
         * 子类重写父类方法
         * 指向子类的父类引用调用fun2时，必定是调用该方法
         */
        public void fun2() {
            System.out.println("JNC 的Fun2...");
        }
    }

//    public class Test {
//        public static void main(String[] args) {
//            Wine a = new JNC();
//            a.fun1();
//            //a.fun1("aaa"); 这个引用是错误的。
//        }
//    }
}
//-------------------------------------------------
//        Output:
//        Wine 的Fun.....
//        JNC 的Fun2...