package com.synchronization.utilities;

import java.util.concurrent.Semaphore;
//produce consumer type
public class PrintOddEvenSemaphore {
	
	Semaphore s1=new Semaphore(0);
	Semaphore s2=new Semaphore(1);
	
	public static void main(String[] args) {
		PrintOddEvenSemaphore pt=new PrintOddEvenSemaphore();
		Thread t1=new Thread(()->{
			for(int i=1;i<10;i+=2) {
				pt.printOdd(i);
			}
		});
		Thread t2=new Thread(()->{
			for(int i=2;i<=10;i+=2) {
				pt.printEven(i);
			}
		});
		t1.start();t2.start();
	}
	void printEven(int c) {
		try {
			s1.acquire();
			System.out.println("Even Number"+c);
			s2.release();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void printOdd(int c){
		try {
			s2.acquire();
			System.out.println("Odd Number"+c);
			s1.release();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
