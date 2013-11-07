package org.liubey.example.multithread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TeacherWithStopwatch {

	public static final int NUMBER_OF_STUDENT = 10;
	public static final int NUMBER_OF_TEACHER = 1;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		CountDownLatch studentSignal = new CountDownLatch(NUMBER_OF_STUDENT);
		CountDownLatch teacherSignal = new CountDownLatch(NUMBER_OF_TEACHER);

		for (int i = 0; i < NUMBER_OF_STUDENT; i++) {
			executor.execute(new Student(i, studentSignal, teacherSignal));
		}

		try {
			System.out.println("各就各位！开跑！");
			teacherSignal.countDown();
			studentSignal.await();
			System.out.println("结果发送到汇报成绩的系统");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}
}
