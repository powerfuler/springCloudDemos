package com.neo.consul.web;

import java.util.concurrent.CountDownLatch;
 
public class Test {
    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        begin.await();// 等待begin的信号
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException ignored) {
                    } finally {
                        end.countDown(); // 每个选手到达终点时，end就减一
                    }
                }
            };
            new Thread(run).start();
        }
        System.out.println("Game Start");
        begin.countDown();      // begin减一，开始游戏
        end.await();            // 等待end变为0，即所有选手到达终点
        System.out.println("Game Over");
    }
}
