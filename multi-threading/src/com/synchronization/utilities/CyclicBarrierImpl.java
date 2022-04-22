package com.synchronization.utilities;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierImpl {
	public static void main(String[] args) {
		CyclicBarrier cb=new CyclicBarrier(3);
		Thread t1=new Thread(new FileRunnable("file 0", cb));
		Thread t2=new Thread(new FileRunnable("file 1", cb));
		Thread t3=new Thread(new FileRunnable("file 2", cb));
		t1.start();t2.start();t3.start();
		Thread t4=new Thread(new FileRunnable("file 4", cb));
		t4.start();
		
		
	}
}
class FileRunnable implements Runnable{
	private String fileName;
	private CyclicBarrier cb;
	
	
	public FileRunnable(String fileName, CyclicBarrier cb) {
		this.fileName = fileName;
		this.cb = cb;
	}


	public void run() {
		System.out.println("Reading file "+fileName+" by Thread "+Thread.currentThread().getName());
		try {
			cb.await();
			System.out.println("After await running by "+Thread.currentThread().getName()+" File "+fileName);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
}

class AfterAction implements Runnable{
	public void run() {
		System.out.println("After Action class");
	}
}
