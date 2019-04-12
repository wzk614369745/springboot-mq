package com.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder=new CountDownLatch(1);
		final CountDownLatch cdAnswer=new CountDownLatch(3);
		for(int i=0;i<3;i++){
			Runnable rn=new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("线程"+Thread.currentThread().getName()+"正准备接受命令");
					try {
						cdOrder.await();
						System.out.println("线程"+Thread.currentThread().getName()+"以接受命令");
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"回应处理结果");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						cdAnswer.countDown();
					}
				}
			};
			service.execute(rn);
		}
		try {
			Thread.sleep((long)(Math.random()*10000));
			System.out.println("线程" + Thread.currentThread().getName() +
                    "即将公布命令");
			cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去运行任务
			System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await(); //命令发送后指挥官处于等待状态。一旦cdAnswer为0时停止等待继续往下运行
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到全部响应结果");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 service.shutdown(); //任务结束。停止线程池的全部线程
		
	}

}
