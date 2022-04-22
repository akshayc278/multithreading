package com.executor.callable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskScheduler {
	public static void main(String[] args) {
		TaskScheduler ts=new TaskScheduler();
		ExecutorService es=Executors.newFixedThreadPool(3);
		Future<String> fts=es.submit(new DoStuff());
		try {
			String str=fts.get();
			System.out.println(str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Callable<String> c1=()->{
			return ts.ReadFile("file 0");
		};
		List<Callable<String>> lst=new LinkedList<>();
		lst.add(c1);
		for(int i=0;i<5;i++) {
			int j=i;
			lst.add(()-> ts.ReadFile("file "+j));
		}
		List<String> lft=new LinkedList<>();
		for(int i=0;i<lst.size();i++) {
			try {
				Future<String> fs=es.submit(lst.get(i));
				lft.add(fs.get());
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lft.stream().forEach(stringStr -> System.out.println(stringStr));
		
		System.out.println("hello main thread");///will be called at end always 
		es.shutdown();
	}
	
	String ReadFile(String fileName) {
		try{
			Thread.sleep(30);
			System.out.println("method submitted");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Reading file: "+fileName+ " Thread "+Thread.currentThread().getName();
	}
}

class DoStuff implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "Akshay";
	}
	
}