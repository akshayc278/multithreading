package com.basic;

public class CodeBlockSyn {
	public void doStuff(String msg) {
		System.out.println(msg);
		synchronized(this) {
			System.out.println(this +Thread.currentThread().getName() );
		try {
			Thread.sleep(30);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
}
class TestThisCode{
	public static void main(String[] args) {
		CodeBlockSyn cbs=new CodeBlockSyn();
		Thread t1=new Thread(()->{
			cbs.doStuff("akshay run 0"+Thread.currentThread().getName());
		});
		Thread t2=new Thread(()->{
			cbs.doStuff("akshay run 1"+Thread.currentThread().getName());
		});
		Thread t3=new Thread(()->{
			cbs.doStuff("akshay run 2"+Thread.currentThread().getName());
		});
		t1.start();t2.start();t3.start();
	}
}
