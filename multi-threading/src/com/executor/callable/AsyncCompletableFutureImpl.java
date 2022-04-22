package com.executor.callable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCompletableFutureImpl {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Void> cf=CompletableFuture.runAsync(()->{
			System.out.println("Running runnable "+Thread.currentThread().getName());
		});
		try {
		cf.get();
		}catch(Exception e) {
			e.printStackTrace();
		}//basic
		System.out.println("call me");
		CompletableFuture<String> cf2=CompletableFuture.supplyAsync(()->{
			return "Running return 2"+Thread.currentThread().getName();
		});
		try {
		System.out.println(cf2.get());
		}catch(Exception e){
			e.printStackTrace();
		}
		CompletableFuture<String> cf3=CompletableFuture.supplyAsync(()->{
			return "Running return 3 "+Thread.currentThread().getName();
		}).thenApply(value -> value.toUpperCase());
		try {
		System.out.println(cf3.get());
		}catch(Exception e){
			e.printStackTrace();
		}
		ExecutorService es=Executors.newFixedThreadPool(1);
		String str=CompletableFuture.supplyAsync(()->{
			
			try {
			Thread.sleep(3000);
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return "Running return 4 "+Thread.currentThread().getName();
		},es).thenApplyAsync(value -> {
			String s=value.toUpperCase();
			s=s.concat(" HELLO THERE");
			System.out.println(s);
			return s;
		},es).get();
		es.shutdown();
		try {
			System.out.println("hi");
		//cf4.get();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Async call"+Thread.currentThread().getName());
	}
}
