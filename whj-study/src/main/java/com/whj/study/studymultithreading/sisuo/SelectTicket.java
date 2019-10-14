package com.whj.study.studymultithreading.sisuo;

/**
 * @description: 余票查询
 * @author: WHJ
 * @create: 2019/10/12
 */
public class SelectTicket {

    private int num;

    public SelectTicket(int num) {
        this.num = num;
    }

    public synchronized void ticketNum(){
        System.out.println(Thread.currentThread().getName()+"剩余票数为:"+num);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
