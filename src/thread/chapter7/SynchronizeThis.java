package thread.chapter7;

import javax.swing.*;

public class SynchronizeThis {

    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        //会快速打印T2，说明没有m2()没有用到锁
        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();

        //T3 | T1 其中之一和T2等带很久，因为T1 T3要抢this锁
        new Thread("T3"){
            @Override
            public void run() {
                thisLock.m3();
            }
        }.start();
    }


}


class ThisLock{

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}