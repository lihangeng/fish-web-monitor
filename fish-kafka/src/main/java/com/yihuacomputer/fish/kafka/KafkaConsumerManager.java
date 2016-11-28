/**
 * 
 */
package com.yihuacomputer.fish.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.mq.IMessagePusher;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.kafka.consumer.LoginKafkaConsumer;
import com.yihuacomputer.fish.kafka.consumer.LogoutKafkaConsumer;
import com.yihuacomputer.fish.kafka.consumer.StatusKafkaConsumer;
import com.yihuacomputer.fish.kafka.consumer.TransactionKafkaConsumer;

/**
 * @author xuxiang
 *
 */
@Service
public class KafkaConsumerManager {

	private ExecutorService threadPool;

	@Autowired
	private KafkaConfig kafkaConfig;

	@Autowired(required = false)
	private HttpServletRequest httpRequest;

	@Autowired
	private IMessagePusher messagePusher;
	private LoginKafkaConsumer loginKafkaConsumer = null;
	private LogoutKafkaConsumer logoutKafkaConsumer = null;
	private StatusKafkaConsumer statusKafkaConsumer = null;
	private TransactionKafkaConsumer transactionKafkaConsumer = null;
	
	@Autowired
	private ISessionManage sessionManage;

	@PostConstruct
	public void init() {
		threadPool = Executors.newFixedThreadPool(4);
		loginKafkaConsumer = new LoginKafkaConsumer(this,sessionManage);
		logoutKafkaConsumer = new LogoutKafkaConsumer(this,sessionManage);
		statusKafkaConsumer = new StatusKafkaConsumer(this);
		transactionKafkaConsumer = new TransactionKafkaConsumer(this);
		threadPool.execute(loginKafkaConsumer);
		threadPool.execute(logoutKafkaConsumer);
		threadPool.execute(statusKafkaConsumer);
		threadPool.execute(transactionKafkaConsumer);
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConfig;
	}

	public void setKafkaConfig(KafkaConfig kafkaConfig) {
		this.kafkaConfig = kafkaConfig;
	}

	public IMessagePusher getMessagePusher() {
		return messagePusher;
	}

	public void setMessagePusher(IMessagePusher messagePusher) {
		this.messagePusher = messagePusher;
	}
	
	@PreDestroy
	public void close(){
		if(loginKafkaConsumer != null){
			loginKafkaConsumer.shutdown();
		}
		if(logoutKafkaConsumer != null){
			logoutKafkaConsumer.shutdown();
		}
		if(transactionKafkaConsumer != null){
			transactionKafkaConsumer.shutdown();
		}
		if(statusKafkaConsumer != null){
			statusKafkaConsumer.shutdown();
		}
		threadPool.shutdown();
	}

}
