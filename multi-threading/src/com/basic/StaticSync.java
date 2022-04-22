package com.basic;

public class StaticSync {
	public static void doStuff(String msg) {
		System.out.println(msg);
		try {
			Thread.sleep(200);
			System.out.println("Hi After Wait"+Thread.currentThread().getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void doStuff2(String msg) {
		System.out.println(msg);
		try {
			Thread.sleep(200);
			System.out.println("Hi After Wait"+Thread.currentThread().getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void doStuff3(String msg) {
		synchronized(StaticSync.class) {
		System.out.println(msg);
		//synchronized(StaticSync.class) {
		try {
			Thread.sleep(200);
			System.out.println("Hi After Wait"+Thread.currentThread().getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
}

class TestStaticSyc{
	public static void main(String[] args) {
		StaticSync s1=new StaticSync();
		StaticSync s2=new StaticSync();
		Thread t1=new Thread(()->{
			//s1.doStuff("Thread 0"+Thread.currentThread().getName());
			//s1.doStuff2("Thread 0"+Thread.currentThread().getName());
			s1.doStuff3("Thread 0"+Thread.currentThread().getName());
		});
		Thread t2=new Thread(()->{
			//s2.doStuff("Thread 1"+Thread.currentThread().getName());
			//s2.doStuff2("Thread 1"+Thread.currentThread().getName());
			s2.doStuff3("Thread 1"+Thread.currentThread().getName());
		});
		t1.start();t2.start();
	}
}
