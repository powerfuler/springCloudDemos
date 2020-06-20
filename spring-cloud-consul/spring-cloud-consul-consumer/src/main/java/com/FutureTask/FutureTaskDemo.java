package com.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			Callable<Integer> c = new Task();
			MyFutureTask ft = new MyFutureTask(c);
			executor.submit(ft);
		}
		executor.shutdown();
	}
}

class MyFutureTask extends FutureTask<Integer> {

	public MyFutureTask(Callable<Integer> callable) {
		super(callable);
	}

	@Override
	protected void done() {
		try {
			System.out.println(get() + " 线程执行完毕！~");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
