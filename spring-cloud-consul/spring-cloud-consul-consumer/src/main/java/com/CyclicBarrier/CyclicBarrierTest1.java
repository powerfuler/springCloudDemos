package com.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author powerful
 * @dec
 *
 */
public class CyclicBarrierTest1 {
	// 自定义工作线程
	private static class Worker extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Worker(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			super.run();

			try {
				System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName() + "开始执行");
				// 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
				Thread.sleep((long) (Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "执行完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int threadCount = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

		for (int i = 0; i < threadCount; i++) {
			System.out.println("创建工作线程" + i);
			Worker worker = new Worker(cyclicBarrier);
			worker.start();
		}
	}
}
