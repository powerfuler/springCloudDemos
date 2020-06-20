package com.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author powerful
 * @dec 模拟了百米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 */

public class PlayersTest {
	public static void main(String[] args) throws InterruptedException {
		// 开始的倒数锁,开始的信号，没有开始都在等待，当开始信号发出(执行countDown方法)，
		final CountDownLatch begin = new CountDownLatch(1);// 控制统一开始，信号发出即开始。
		// 结束的倒数锁，所有的人跑完，所有的线程结束，执行完成。
		final CountDownLatch end = new CountDownLatch(10);// 控制统一结束，所有人跑完结束。
		// 模拟出10个线程代表10个参赛选手
		for (int index = 0; index < 10; index++) {
			final int num = index + 1;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// 如果当前计数为零，则此方法立即返回。
						begin.await();// 等待开始命令
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("No." + num + " arrived");
					} catch (InterruptedException ignored) {
					} finally {
						end.countDown(); // 选手到达终点时，end就减一
					}
				}
			}).start();
		}
		
		System.out.println("Game Start");
		begin.countDown(); // begin减一，开始游戏
		
		System.out.println("players is running!");
		
		end.await(); // 等待end变为0，即所有选手到达终点
		System.out.println("Game Over");
	}
}
