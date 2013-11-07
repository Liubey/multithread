package org.liubey.example.multithread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphorePool {

	private Semaphore canExecuteCount = new Semaphore(5, true);

	private static final int TRY_EXECUTE_TIMEOUT = 20;

	public boolean applyResource() {
		boolean canExecute = false;
		try {
			canExecute = canExecuteCount.tryAcquire(1, TRY_EXECUTE_TIMEOUT,
					TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		return canExecute;

	}

	public void releaseResource() {
		canExecuteCount.release(1);
	}
}
