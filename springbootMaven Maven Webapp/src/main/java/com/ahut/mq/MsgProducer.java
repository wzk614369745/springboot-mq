package com.ahut.mq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ahut.entity.User;
/**
 * @author issuser
 *消息的生产者
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback{
	private RabbitTemplate rabbittemplate;
	 @Autowired
	    public MsgProducer(RabbitTemplate rabbitTemplate) {
	        this.rabbittemplate = rabbitTemplate;
	        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
	    }

	@Override
	public void confirm(CorrelationData correlationData, boolean ack,
			String cause) {
		// TODO Auto-generated method stub
		System.out.println("回调ID:"+correlationData);
		if(ack){
			System.out.println("消息成功消费");
		}else{
			System.out.println("消息消费失败"+cause);
		}
	}
	public void sendMsg(String obj){
		CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());
		//把消息放入exchange_a对应的队列当中去，对应的是队列A
		rabbittemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_B,obj,correlationId);	
	}	
}
