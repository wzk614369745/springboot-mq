package com.ahut.mq;

import java.io.IOException;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.rabbitmq.client.Channel;
@Configuration
public class RabbitConfig {
	@Value("${spring.rabbitmq.host}")
	private String host;
	@Value("${spring.rabbitmq.port}")
	private int port;
	@Value("${spring.rabbitmq.username}")
	private String userName;
	@Value("${spring.rabbitmq.password}")
	private String password;
	public static final String EXCHANGE_A="my-mq-exchange-A";
	public static final String EXCHANGE_B="my-mq-exchange-B";
	public static final String EXCHANGE_C="my-mq-exchange-C";
	
	public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";
    
    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";
    /*
     * 针对消费者配置
     * 1.设置交换机类型
     * 2.将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     	HeadersExchange ：通过添加属性key-value匹配
     	DirectExchange:按照routingkey分发到指定队列
     	TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange(){
    	return new DirectExchange(EXCHANGE_A);
    } 
    @Bean
    public Queue queue(){
    	return new Queue(QUEUE_B, true);
    }
    @Bean
    public Binding binding(){
    	return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_B);
    }
    
    
    @Bean
    public ConnectionFactory connectionfaction(){
    	//CachingConnectionFactory connectionFactory=new CachingConnectionFactory();
    	CachingConnectionFactory connectionFactory=new CachingConnectionFactory(host, port);
    	connectionFactory.setUsername(userName);
    	connectionFactory.setPassword(password);
    	connectionFactory.setVirtualHost("/");
    	connectionFactory.setPublisherConfirms(true);
    	return connectionFactory;
    }
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public  RabbitTemplate rabbitTemplate(){
    	RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionfaction());
    	return rabbitTemplate;
    }
}
