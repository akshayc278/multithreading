package com.locks;

import java.util.concurrent.locks.ReentrantLock;

public class RetrantLockImpl {
	public static void main(String[] args) {
		ReentrantLock r=new ReentrantLock();
		Thread t1=new Thread(new Display(r,"Thread 1"),"Thread 1");
		Thread t2=new Thread(new Display(r,"Thread 1"),"Thread 1");
		t1.start();t2.start();
	}
}

class Display implements Runnable{
	private String threadName;
	ReentrantLock lock;
	
	Display(ReentrantLock lock,String threadName){
		this.lock=lock;
		this.threadName=threadName;
	}
	@Override
	public void run() {
		System.out.println("In diplay Run method");
		lock.lock();
		try {
			System.out.println("Lock "+lock.getHoldCount()+" is aquired by "+Thread.currentThread().getName());
			methodA();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	private void methodA() {
		lock.tryLock();
		try {
			System.out.println("inside methodA of Runnable and lock count is "+lock.getHoldCount()+" by "+Thread.currentThread().getName());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}
