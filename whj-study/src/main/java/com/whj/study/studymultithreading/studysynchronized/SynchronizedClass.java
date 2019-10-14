package com.whj.study.studymultithreading.studysynchronized;

/**
 * @description: 学习同步代码块this锁
 * @author: WHJ
 * @create: 2019/10/13
 */
public class SynchronizedClass implements Runnable {

    private  Integer count=50;
    private Object object=new Object();
    @Override
    public void run() {

        synchronized (object){
            while(count>0){
                System.out.println(Thread.currentThread().getName()+"现在出售第"+count+"张票");
                count--;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
