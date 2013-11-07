package org.liubey.example.multithread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Student implements Runnable {
	private int id;
	private CountDownLatch studentSignal;
	private CountDownLatch teacherSignal;

	public Student(int id, CountDownLatch studentSignal,
			CountDownLatch teacherSignal) {
		this.id = id;
		this.studentSignal = studentSignal;
		this.teacherSignal = teacherSignal;
	}

	@Override
	public void run() {
		try {
			teacherSignal.await();
			System.out.println("学生" + id + "起跑...");
			System.out.println("学生" + id + "到达终点。");
			studentSignal.countDown();
			System.out.println("学生" + id + "继续干其他事情");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}