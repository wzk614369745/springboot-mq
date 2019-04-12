package com.test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahut.HelloApplication;
import com.ahut.dao.UserMapper;
import com.ahut.entity.User;
import com.ahut.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes={HelloApplication.class})
public class CeShi {
	@Autowired
	UserService user;
	@Autowired
	UserMapper mapper;
	private static CountDownLatch cdAnswer=new CountDownLatch(20);
	ExecutorService service = Executors.newCachedThreadPool();
	@Test
	public void getUser() throws InterruptedException{
		for(int i=0;i<20;i++){
			Million m=new Million();
			new Thread(m).start();
			cdAnswer.countDown();
		}
		Thread.currentThread().sleep(1000);
		System.out.println("执行完毕");
	}
	public class Million implements Runnable{
		@Override
		public void run() {
			try {
				System.out.println("线程"+Thread.currentThread().getName()+"正在等待");
				cdAnswer.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user2 = mapper.user(1);
			System.out.println(user2.getName());
			
			// TODO Auto-generated method stub
		}
	}
	

}
