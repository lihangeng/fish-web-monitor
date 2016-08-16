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
	private KafkaConsumer kafkaConsumer = null;

	@PostConstruct
	public void init() {
		threadPool = Executors.newFixedThreadPool(1);
		kafkaConsumer = new KafkaConsumer(this);
		threadPool.submit(kafkaConsumer);
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
		if(kafkaConsumer != null){
			kafkaConsumer.shutdown();
		}
	}

}
