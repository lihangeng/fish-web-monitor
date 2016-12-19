package com.yihuacomputer.fish.kafka.consumer;

import java.util.List;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.api.session.LoginMessage;
import com.yihuacomputer.fish.kafka.KafkaConsumerManager;
import com.yihuacomputer.fish.kafka.TopicType;

public class LogoutKafkaConsumer extends KafkaConsumerConfig implements Runnable {

	private Logger logger = LoggerFactory.getLogger(LogoutKafkaConsumer.class);
	private ISessionManage sessionManage;


	public LogoutKafkaConsumer(KafkaConsumerManager kafkaConsumerManager, ISessionManage sessionManage) {
		super(kafkaConsumerManager.getKafkaConfig());
		this.sessionManage = sessionManage;
	}

	public void run() {
		logger.info(" LogoutKafkaConsumer run");
		TopicType topicType = TopicType.LOGOUT;
		List<KafkaStream<byte[], byte[]>> streamsList = getStreams(topicType);
		for(KafkaStream<byte[], byte[]>streams:streamsList){
			 ConsumerIterator<byte[], byte[]> it = streams.iterator();
			 while (it.hasNext()) {
					byte[] bytes = it.next().message();
					String message = new String(bytes);
					if (!"".equals(message.trim())) {
						post(message);
					}
			}
		}
	}

	private void post(String msg) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("mq info [%s]", msg));
		}
		try {
			LoginMessage loginMessage = JsonUtils.fromJson(msg, LoginMessage.class);
			sessionManage.logoutByNotice(loginMessage);
		} catch (Exception e) {
			logger.error(String.format("mq handle error [%s]", e));
		}
	}

}
