package com.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author powerful
 * @dec 四个游戏玩家玩游戏，游戏有三个关卡，每个关卡必须要所有玩家都到达后才能允许通过。如果有玩家A先到了关卡1，他必须等到其他所有玩家都到达关卡1时才能通过，也就是说线程之间需要相互等待。
 *      这和CountDownLatch的应用场景有区别，CountDownLatch里的线程是到了运行的目标后继续干自己的其他事情，而这里的线程需要等待其他线程后才能继续完成下面的工作。
 *
 */
class Player implements Runnable {
	private CyclicBarrier cyclicBarrier;
	private int id;

	public Player(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("玩家" + id + "正在玩第一关...");

			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("玩家第一关已结束" + id + "到达第二关...");
			cyclicBarrier.await();
			Thread.sleep(3000);
			System.out.println("玩家" + id + "进入第二关...");

			System.out.println("玩家第二关已结束" + id + "到达第三关...");
			cyclicBarrier.await();
			Thread.sleep(4000);
			System.out.println("玩家" + id + "进入第三关...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}

public class CyclicBarrierTest {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "最后到达， 完成最后任务！");
			}
		});

		// 4个玩家
		for (int i = 0; i < 4; i++) {
			System.out.println("创建玩家" + i);
			new Thread(new Player(i, cyclicBarrier)).start();
		}
	}
}