package com.automic.type;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutomicIntegerImpl {
	public static void main(String[] args) {
		AtomicInteger ai=new AtomicInteger(0);
		AtomicLong al=new AtomicLong();
		Thread t1=new Thread (()->{
			int j=ai.incrementAndGet();
			System.out.println("Integer value"+j);
			long l=al.incrementAndGet();
			System.out.println("Long value"+l);
		});
		Thread t2=new Thread (()->{
			int j=ai.incrementAndGet();
			System.out.println("Integer Value"+j);
			long l=al.incrementAndGet();
			System.out.println("Long value"+l);
		});
		t2.start();t1.start();
	}
}
