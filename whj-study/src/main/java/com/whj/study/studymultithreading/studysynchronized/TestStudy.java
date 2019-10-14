package com.whj.study.studymultithreading.studysynchronized;

/**
 * @description:
 * @author: WHJ
 * @create: 2019/10/13
 */
public class TestStudy {
    public static void main(String[] args) {
        SynchronizedClass synchronizedClass=new SynchronizedClass();
        Thread thread1=new Thread(synchronizedClass);
        Thread thread2=new Thread(synchronizedClass);
        thread1.start();
        thread2.start();
    }
}
