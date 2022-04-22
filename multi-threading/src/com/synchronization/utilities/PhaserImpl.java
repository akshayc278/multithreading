package com.synchronization.utilities;

import java.util.concurrent.Phaser;

//CountDownLatch and CyclicBarrier number of parties (thread) that are registered for waiting can't change where as in Phaser that number can vary

public class PhaserImpl {
	public static void main(String[] args) {
		Phaser ph =new Phaser(1);
		int curPhase=ph.getPhase();
		Thread t0=new Thread(new FileRunner("file 0",ph));
		Thread t1=new Thread(new FileRunner("file 1",ph));
		Thread t2=new Thread(new FileRunner("file 2",ph));
		System.out.println(curPhase);
		
		t0.start();t1.start();t2.start();
		ph.arriveAndAwaitAdvance();
		curPhase=ph.getPhase();
		Thread t3=new Thread(new FileRunner("file 3",ph));
		Thread t4=new Thread(new FileRunner("file 4",ph));
		System.out.println(curPhase);
		
		t3.start();t4.start();
		ph.arriveAndAwaitAdvance();
		ph.arriveAndDeregister();
	}
}
class FileRunner implements Runnable{
	private String fileName;
	private Phaser ph;
	FileRunner(String fileName,Phaser ph){
		this.fileName=fileName;
		this.ph=ph;
		ph.register();
	}
	public void run() {
		System.out.println("Run method of fileRunner "+fileName);
		ph.arriveAndAwaitAdvance();
		
		ph.arriveAndDeregister();
	}
}