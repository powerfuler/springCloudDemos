package com.FutureTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureCallDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("主线程" + Thread.currentThread().getName() + "开始执行!");

		ExecutorService executor2 = Executors.newFixedThreadPool(5);
		class Task implements Callable<String> {
			@Override
			public String call() throws Exception {
				System.out.println("子线程" + Thread.currentThread().getName() + "开始运算");

				Random rand = new Random();
				TimeUnit.SECONDS.sleep(rand.nextInt(1));
				return Thread.currentThread().getName();
			}
		}

		List<Future<String>> results = new ArrayList<Future<String>>();

		Future<String> f = null;
		for (int i = 0; i < 5; i++) {
			System.out.println("开始创建子线程" + i);
			f = executor2.submit(new Task());
			results.add(f);
		}

		boolean flag = true;
		while (flag) {
			for (Iterator<Future<String>> iter = results.iterator(); iter.hasNext();) {
				f = iter.next();
				if (f.isDone()) {
					System.out.println(f.get());
					iter.remove();
				}
			}
			if (results.size() == 0) {
				flag = false;
				System.out.println("我是最后一个任务执行完了，可以记录日志了" + f.get());

			}
		}

		executor2.shutdownNow();

		System.out.println("task运行结果" + f.get());

		System.out.println("主线程" + Thread.currentThread().getName() + "执行结束!");
	}
}