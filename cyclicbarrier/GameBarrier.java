package org.liubey.example.multithread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameBarrier {

	public static final int NUMBER_OF_PLAYERS = 4;
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PLAYERS);
		CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_PLAYERS, new Runnable() {
			@Override
			public void run() {
				System.out.println("所有玩家通过第一关！");
			}
		});

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			executor.execute(new Player(i, barrier));
		}
		
		executor.shutdown();
		
	}

}