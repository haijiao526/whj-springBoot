package com.whj.study.studymultithreading.sisuo;

/**
 * @description: 售票
 * @author: WHJ
 * @create: 2019/10/12
 */
public class SaleTicket {

    private int num;

    public SaleTicket(int num) {
        this.num = num;
    }

    /**
     * 线程同步售票
     */
    public synchronized void saleTicket() {
        System.out.println(Thread.currentThread().getName()+"出售第" + num + "张票");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
