package com.basic;

public class MethodSyc {
	public void doStuff(String msg) {
		System.out.println(msg);
		try {
		Thread.sleep(1);
		System.out.println(msg+"After sleep output");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void doStuff2(String msg) {
		System.out.println(msg);
		try {
		Thread.sleep(1);
		System.out.println(msg+"After sleep output");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

class TestThis{
	public static void main(String[] args) {
		MethodSyc ms=new MethodSyc();
		Thread t1=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff("Akshay run1"+Thread.currentThread().getName());
			}
			
		});
		Thread t2=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff("Akshay run2"+Thread.currentThread().getName());
			}
			
		});
		Thread t3=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff("Akshay run3"+Thread.currentThread().getName());
			}
			
		});
		t1.start();t2.start();t3.start();
		try {
		Thread.currentThread().sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("using sync method ");
		Thread t4=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff2("Akshay run4"+Thread.currentThread().getName());
			}
			
		});
		Thread t5=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff2("Akshay run5"+Thread.currentThread().getName());
			}
			
		});
		Thread t6=new Thread(new Runnable() {

			@Override
			public void run() {
				ms.doStuff2("Akshay run6"+Thread.currentThread().getName());
			}
			
		});
		t4.start();t5.start();t6.start();
	}
}