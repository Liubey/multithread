package com.guang.eunormia.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CustomFuture<T> implements Future<T> {

    CountDownLatch cdl = new CountDownLatch(1);
    volatile T result;
    volatile Throwable t;
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isCancelled() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isDone() {
		return cdl.getCount() <= 0;
	}

	@Override
	public T get() throws InterruptedException, ExecutionException {
		cdl.await();
        if (t != null) {
            throw new ExecutionException(t);
        }
        return result;
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
        boolean to = cdl.await(timeout, unit);
        if (!to) {
            throw new TimeoutException("future cdl timeout");
        }
        if (t != null) {
            throw new ExecutionException(t);
        }
        return result;
	}
	
    public void setResult(final T result) {
        this.result = result;
        cdl.countDown();
    }

    public void setException(final Throwable t) {
        this.t = t;
        cdl.countDown();
    }
}
