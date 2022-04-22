package com.executor.callable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class InvokeAllAsyncImpl {
	public static void main(String[] args) {
		InvokeAllAsyncImpl imp=new InvokeAllAsyncImpl();
		List<Callable<String>> lrn=new LinkedList<>();
		for(int i=0;i<10;i++) {
			int j=i;
			lrn.add(()->{
				Thread.sleep(3000);
				return imp.methodOne("file"+j);
			});
		}
		List<Future<String>> lf=Collections.emptyList();
		ExecutorService es=Executors.newFixedThreadPool(5);
		try {
			lf=es.invokeAll(lrn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Main Thread call 1");
		
		es.shutdown();
		List<String> lstr=lf.stream().map(x-> {
			try {
				return x.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		
		lstr.stream().forEach(v-> System.out.println(v));
		System.out.println("Main Thread call 2");
	}
	String methodOne(String fileName) {
		System.out.println("Method submitted");
		return "Running "+fileName;
	}
}
