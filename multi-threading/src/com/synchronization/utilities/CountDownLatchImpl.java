package com.synchronization.utilities;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchImpl {
	public static void main(String[] args) {
		CountDownLatch cdl=new CountDownLatch(3);
		Thread t1=new Thread(new FileReaderClass("file 1",cdl));
		Thread t2=new Thread(new FileReaderClass("file 2",cdl));
		Thread t3=new Thread(new FileReaderClass("file 3",cdl));
		t1.start();t2.start();t3.start();
		try {
		cdl.await();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Printing this after file read ");
	}
	
}
class FileReaderClass implements Runnable{
	private CountDownLatch cdl;
	private String fileName;
	
	FileReaderClass(String fileName,CountDownLatch cdl){
		this.fileName=fileName;
		this.cdl=cdl;
	}
	@Override
	public void run() {
		System.out.println("reading from File "+fileName);
		cdl.countDown();
	}
	
}
