package com.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author powerful
 * @dec 模拟多线程运算，异步分段计算，线程1实现 1加到1000，线程2实现 1000加到2000，线程3实现 2000加到5000，线程4实现
 *      线程1、2、3计算结果的和。 这个和上个例子不同，不需要统一的开始时间，只要线程1和2完成运算，线程3才能运算。
 *      
 *      线程3 : 线程3开始运算
		线程2 : 线程2开始运算
		线程1 : 线程1开始运算
		线程4 : 开始等待结果
		线程3 : 线程3运算结束: 10498500
		线程2 : 线程2运算结束: 1499500
		线程1 : 线程1运算结束: 499500
		线程4 : 开始计算总和
		线程4 : 线程4运算结束: 12497500
 * 
 */

public class CountDownLatchMultithreadOperation {
	private CountDownLatch countDownLatch;
	private int Num1 = 1, Num2 = 1000, Num3 = 2000, Num4 = 5000;
	private volatile int tmpRes1, tmpRes2, tmpRes3;

	// 计算开始数值到结束数值的和
	private int add(int startNum, int endNum) {
		int sum = 0;
		for (int i = startNum; i < endNum; i++) {
			sum += i;
		}
		return sum;
	}

	// 统计所有的和的值
	private int totalSum(int a, int b, int c) {
		return a + b + c;
	}

	public void calculate() {
		countDownLatch = new CountDownLatch(3);// 有3个需要处理计算的线程，第4个线程等待前3个线程运算结果

		Thread thread1 = new Thread(() -> {
			try {
				// 确保线程3先与1，2执行，由于countDownLatch计数不为0而阻塞
				System.out.println(Thread.currentThread().getName() + " : 线程1开始运算");
				Thread.sleep((long) (Math.random() * 10000));
				tmpRes1 = add(Num1, Num2);
				System.out.println(Thread.currentThread().getName() + " : 线程1运算结束: " + tmpRes1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		}, "线程1");

		Thread thread2 = new Thread(() -> {
			try {
				// 确保线程3先与1，2执行，由于countDownLatch计数不为0而阻塞
				System.out.println(Thread.currentThread().getName() + " : 线程2开始运算");
				Thread.sleep(1000);
				tmpRes2 = add(Num2, Num3);
				System.out.println(Thread.currentThread().getName() + " : 线程2运算结束: " + tmpRes2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		}, "线程2");

		Thread thread3 = new Thread(() -> {
			try {
				// 确保线程3先与1，2执行，由于countDownLatch计数不为0而阻塞
				System.out.println(Thread.currentThread().getName() + " : 线程3开始运算");
				Thread.sleep(100);
				tmpRes3 = add(Num3, Num4);
				System.out.println(Thread.currentThread().getName() + " : 线程3运算结束: " + tmpRes3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		}, "线程3");

		Thread thread4 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " : 开始等待结果");
				countDownLatch.await();
				System.out.println(Thread.currentThread().getName() + " : 开始计算总和");
				int ans = totalSum(tmpRes1, tmpRes2, tmpRes3);
				System.out.println(Thread.currentThread().getName() + " : 线程4运算结束: " + ans);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "线程4");

		thread3.start();
		thread4.start();
		thread1.start();
		thread2.start();

	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatchMultithreadOperation demo = new CountDownLatchMultithreadOperation();
		demo.calculate();

		Thread.sleep(10000);
	}
}