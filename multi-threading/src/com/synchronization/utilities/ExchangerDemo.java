package com.synchronization.utilities;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	  public static void main(String[] args) {
	    Exchanger<String> ex = new Exchanger<String>();
	    // Starting two threads
	    new Thread(new ProducerThread(ex)).start();
	    new Thread(new ConsumerThread(ex)).start();
	    
	  }
	}

	class ProducerThread implements Runnable {
	  String str;
	  Exchanger<String> ex;
	  ProducerThread(Exchanger<String> ex){
	    this.ex = ex;
	    str = new String();
	  }
	  @Override
	  public void run() {
	    for(int i = 0; i < 3; i ++){
	      str = "Producer" + i;
	      try {
	        // exchanging with an empty String 
	        str = ex.exchange(str);
	      } catch (InterruptedException e) {
	        System.out.println(e);
	      }
	    }       
	  }   
	}

	class ConsumerThread implements Runnable {
	  String str;
	  Exchanger<String> ex;
	  ConsumerThread(Exchanger<String> ex){
	    this.ex = ex;
	  }
	  @Override
	  public void run() {
	    for(int i = 0; i < 2; i ++){
	      try {
	        // Getting string from producer thread
	        // giving empty string in return
	        str = ex.exchange(new String());
	        System.out.println("Got from Producer " + str);
	      } catch (InterruptedException e) {
	        System.out.println(e);
	      }
	    }        
	  }   
	}