package com.synchronization.utilities;

import java.util.concurrent.Semaphore;

public class NormalSemaphore {
	public static void main(String[] args) {
		Semaphore sp=new Semaphore(1);
		System.out.println("Lock aquired now available "+sp.availablePermits());
		Thread t1=new Thread(()->{
			try{
				sp.acquire();
				System.out.println("Lock aquired now available "+sp.availablePermits());
				sp.release();
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		//
		Thread t2=new Thread(()->{
			try{
				sp.acquire();//acquired by t2 thread
				System.out.println("Lock aquired now available "+sp.availablePermits());
				//sp.release();
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		sp.release();//released by main thread
		t1.start();t2.start();
	}

}
