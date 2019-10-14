package com.whj.study.studymultithreading.studysynchronized;

/**
 * @description: 学习threadLocal
 * @author: WHJ
 * @create: 2019/10/14
 */
class Res{
    private  ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        //初始化
        @Override
        protected Integer  initialValue() {
            return 0;
        }
    };
    public synchronized Integer getNum(){
        Integer num=threadLocal.get()+1;
        threadLocal.set(num);
        return num;
    }
}

public class StustyThreadLocal extends Thread{
        private Res res;
        public StustyThreadLocal(Res res){
            this.res=res;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++)
            System.out.println(Thread.currentThread().getName()+":"+res.getNum());
        }

    public static void main(String[] args) {
        Res res=new Res();
        Thread thread0=new StustyThreadLocal(res);
        Thread thread1=new StustyThreadLocal(res);
        thread0.start();
        thread1.start();
    }
}
