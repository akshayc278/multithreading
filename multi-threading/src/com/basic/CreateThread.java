package com.basic;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThread {
	public static void main(String[] args) {
		ThreadOne one = new ThreadOne();
		one.start();
		ThreadTwo two = new ThreadTwo();
		two.start();
		ThreadThree three=new ThreadThree();
		Thread t1=new Thread(three);
		t1.start();
		NormalClass nc=new NormalClass();
		Thread t2=null;
		for(int i=0;i<5;i++) {
			int j=i;
			Runnable r=()->nc.printValue(j);
			t2=new Thread(r);
			t2.start();
		}
		System.out.println(t2.getState());
		Runnable r2=()-> {
			System.out.println("this is runnable r2");
		};
		
		t2=new Thread(r2);
		t2.start();
		//Now if we don't need to take casee wait sleep notify etc in executor service 
		
		ExecutorService es=Executors.newFixedThreadPool(3);
		for(int i=0;i<5;i++) {
			int j=i;
			es.execute(()-> {
				System.out.println("this is runnable r3"+j);
			});
		}
		es.shutdown();
		
	}
}

class ThreadOne extends Thread {
	public void run() {
		System.out.println("class ThreadOne");
	}
}

class ThreadTwo extends Thread {
	{
		System.out.println("default code");
	}
	public void run() {
		System.out.println("class ThreadTwo");
	}
}
class ThreadThree implements Runnable{

	@Override
	public void run() {
		System.out.println("class ThreadThree");
	}
	
}
//best implementation because runnable is functional interface
class NormalClass {
	public void printValue(int i) {
		System.out.println("value is "+i);
	}
}