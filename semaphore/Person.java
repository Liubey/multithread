package org.liubey.example.multithread.semaphore;

public class Person implements Runnable {

	private SemaphorePool pool;
	private int id;

	public Person(int id, SemaphorePool pool) {
		this.pool = pool;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			pool.applyResource();
			System.out.println("人物" + id + "进入");
			Thread.sleep(1000);
			System.out.println("人物" + id + "离开");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pool.releaseResource();
		}
	}
}