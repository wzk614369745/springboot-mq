package com.ahut.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ahut.entity.User;

@Component
@RabbitListener(queues=RabbitConfig.QUEUE_B)
public class MsgReciver {
	@RabbitHandler
	public void process(String object){
		System.out.println(object);
	}

}
