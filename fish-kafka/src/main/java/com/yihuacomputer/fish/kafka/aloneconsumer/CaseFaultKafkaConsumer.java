package com.yihuacomputer.fish.kafka.aloneconsumer;

import java.util.List;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.KafkaConsumerManager;
import com.yihuacomputer.fish.kafka.TopicType;
import com.yihuacomputer.fish.kafka.consumer.LoginKafkaConsumer;

public class CaseFaultKafkaConsumer extends KafkaAloneConsumerConfig implements Runnable {
	private Logger logger = LoggerFactory.getLogger(LoginKafkaConsumer.class);
	private KafkaConsumerManager kafkaConsumerManager;
	

	public CaseFaultKafkaConsumer(KafkaConsumerManager kafkaConsumerManager) {
		super(kafkaConsumerManager.getKafkaConfig());
		this.kafkaConsumerManager = kafkaConsumerManager;
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConsumerManager.getKafkaConfig();
	}

	@Override
	public void run() {
		logger.info(" CaseFaultKafkaConsumer run");
		TopicType topicType = TopicType.CASEFAULT;
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
			//TODO 收到故障信息后的处理动作
        } catch (Exception e) {
            logger.error(String.format("mq handle error [%s]", e));
        }
	}

}
