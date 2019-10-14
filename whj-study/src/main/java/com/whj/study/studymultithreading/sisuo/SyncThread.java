package com.whj.study.studymultithreading.sisuo;

/**
 * @description: 线程测试死锁
 * @author: WHJ
 * @create: 2019/10/12
 */
public class SyncThread implements Runnable {

    private SaleTicket saleTicket;
    private SelectTicket selectTicket;
    private boolean flag;

    public SyncThread(SaleTicket saleTicket, SelectTicket selectTicket, boolean flag) {
        this.saleTicket = saleTicket;
        this.selectTicket = selectTicket;
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            //如果是true先买票后查余票，即为获取买票业务的锁
            saleTicket.saleTicket();
            //查余票获取查询票类的锁
            selectTicket.ticketNum();
        }
        //如果是false先买票后查余票，即为获取买票业务的锁
        else {
            selectTicket.ticketNum();
            saleTicket.saleTicket();
        }
    }
}
