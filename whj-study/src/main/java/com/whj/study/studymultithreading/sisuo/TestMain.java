package com.whj.study.studymultithreading.sisuo;

/**
 * @description: 测试死锁
 * @author: WHJ
 * @create: 2019/10/12
 */
public class TestMain {

    public static void main(String[] args) {
        SaleTicket saleTicket= new SaleTicket(100);
         SelectTicket selectTicket = new SelectTicket(20);
        Thread thread1=new Thread(new SyncThread(saleTicket,selectTicket,true));
        Thread thread2=new Thread(new SyncThread(saleTicket,selectTicket,false));
        thread1.start();
        thread2.start();
    }
}
