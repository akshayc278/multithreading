package com.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockImpl {
	int c = 0;

	public static void main(String[] args) {
		StampedLock sl=new StampedLock();
		ExecutorService es=Executors.newFixedThreadPool(2);
		StampedLockImpl sll=new StampedLockImpl();
		
		Runnable r1=()->{
			long stamp=sl.readLock();
			try {
				System.out.println("In read block"+sll.getValue());
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				sl.unlock(stamp);
			}
		};
		Runnable r2=()->{
			long stamp=sl.writeLock();
			try {
				sll.increament();
				System.out.println("In write block"+sll.getValue());
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				sl.unlock(stamp);
			}
		};
		
		es.submit(r1);
		es.submit(r2);
		es.submit(r2);
		es.execute(r1);
		es.execute(r1);
		es.shutdown();
		System.out.println(sll.distanceFromOrigin(sl, 1, 1));
		sll.moveIfAtOrigin(sl, 1, 1);
		
		
		
	}
	
	private void increament() {
		c++;
	}

	private Integer getValue() {
		return c;
	}
	
	double distanceFromOrigin(StampedLock sl,double x,double y) { // A read-only method
		  long stamp = sl.tryOptimisticRead();
		  double currentX = x, currentY = y;
		  if (!sl.validate(stamp)) {
		    stamp = sl.readLock();
		    try {
		      currentX = x;
		      currentY = y;
		    } finally {
		      sl.unlockRead(stamp);
		    }
		  }
		  return Math.sqrt(currentX * currentX + currentY * currentY);
		}
	
	void moveIfAtOrigin(StampedLock sl,double newX, double newY) { // upgrade
		  // Could instead start with optimistic, not read mode
			double x=0.0,y=0.0;
		  long stamp = sl.readLock();
		  try {
		    while (x == 0.0 && y == 0.0) {
		      long ws = sl.tryConvertToWriteLock(stamp);
		      if (ws != 0L) {
		        stamp = ws;
		        x = newX;
		        y = newY;
		        System.out.println("inside conversion");
		        break;
		      }
		      else {
		        sl.unlockRead(stamp);
		        stamp = sl.writeLock();
		      }
		    }
		  } finally {
		    sl.unlock(stamp);
		  }
		}
}
