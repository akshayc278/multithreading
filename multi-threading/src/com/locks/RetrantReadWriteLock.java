package com.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class RetrantReadWriteLock {
	public static void main(String[] args) {
		ReentrantReadWriteLock rrw=new ReentrantReadWriteLock();
		ReadLock rl=rrw.readLock();
		WriteLock wl=rrw.writeLock();
		
		rl.lock();
		try {
			//read ops
		}finally {
			rl.unlock();
		}
		wl.lock();
		try {
			//write block
		}finally {
			wl.unlock();
		}
	}
}
