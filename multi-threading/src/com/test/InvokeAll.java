package com.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAll {

	public String doStuff(String value) {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "value is " + value + " " + Thread.currentThread().getName();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		InvokeAll iv = new InvokeAll();
		List<Callable<String>> listOfCall = new LinkedList<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		ExecutorService executor2 = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			int j = i;
			Callable<String> call = () -> iv.doStuff(String.valueOf(j));
			listOfCall.add(call);
		}

		List<Future<String>> fll2 = new LinkedList<>();
		System.out.println("Invoking invokeall from executor");

		try {
			long time1 = System.currentTimeMillis();
			List<Future<String>> fll = executor.invokeAll(listOfCall);
			for (int i = 0; i < fll.size(); i++) {
				String sf = fll.get(i).get();//getting done in same 304 millisec
				System.out.println(sf);

			}

			long time2 = System.currentTimeMillis();
			System.out.println(time2 - time1);

			System.out.println("Invoking submit method");

			long time3 = System.currentTimeMillis();
			for (int i = 0; i < listOfCall.size(); i++) {
				Future<String> s = executor2.submit(listOfCall.get(i));
				fll2.add(s);
				// System.out.println(s.get());//uncomment this will get time increase to 1002
				// milliseconds no parallelism after get loop will halt till get is getting
				// output
			}
			for (int i = 0; i < fll2.size(); i++) {
				System.out.println(fll2.get(i).get());// two for loop one for add and one for get will act as invokeall
														// call (Async) 304 millisec
			}
			long time4 = System.currentTimeMillis();
			System.out.println(time4 - time3);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
			executor2.shutdown();

		}

	}
}
