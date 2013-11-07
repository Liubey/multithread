package org.liubey.example.multithread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSemaphore {

	public static final int NUMBER_OF_PERSONS = 10;
	public static final SemaphorePool pool = new SemaphorePool();

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PERSONS);
		
		for(int i=0;i<NUMBER_OF_PERSONS;i++) {
			executor.execute(new Person(i, pool));
		}
		executor.shutdown();
	}

}
