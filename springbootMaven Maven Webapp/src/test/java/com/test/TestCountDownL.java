package com.test;

import java.util.concurrent.CountDownLatch;

public class TestCountDownL {
public static void main(String[] args) {
		
		//所有线程阻塞，然后统一开始
		final CountDownLatch begin = new CountDownLatch(1);
		
		//主线程阻塞，直到所有分线程执行完毕
		final CountDownLatch end = new CountDownLatch(5);
		
		for(int i = 0; i < 5; i++){
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						begin.await();
						System.out.println(Thread.currentThread().getName() + " 起跑");
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + " 到达终点");
						end.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			
			thread.start();
		}
		
		try {
			System.out.println("1秒后统一开始");
			Thread.sleep(1000);
			begin.countDown();
 
			end.await();
			System.out.println("停止比赛");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


}
