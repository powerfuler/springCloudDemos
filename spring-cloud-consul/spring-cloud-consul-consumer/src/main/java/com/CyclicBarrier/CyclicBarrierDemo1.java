package com.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author powerful
 * @dec 模拟5个线程，各个到达A等待其他线程到达A，线程阻塞在这里，等所有线程都到达A，然后每个线程继续执行，到达B，每个线程共同等待其他线程
 *
 */
public class CyclicBarrierDemo1 {

	static class TaskThread extends Thread {

		CyclicBarrier barrier;

		public TaskThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println(getName() + " 到达栅栏 A");
				barrier.await();
				System.out.println(getName() + " 冲破栅栏 A");

				Thread.sleep(2000);
				System.out.println(getName() + " 到达栅栏 B");
				barrier.await();
				System.out.println(getName() + " 冲破栅栏 B");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int threadNum = 5;
		CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "最后到达， 完成最后任务");
			}
		});

		for (int i = 0; i < threadNum; i++) {
			new TaskThread(barrier).start();
		}
	}

}