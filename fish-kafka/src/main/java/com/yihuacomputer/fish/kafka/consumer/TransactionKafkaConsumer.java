package com.yihuacomputer.fish.kafka.consumer;

import java.util.List;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.KafkaConsumerManager;
import com.yihuacomputer.fish.kafka.TopicType;

public class TransactionKafkaConsumer extends KafkaConsumerConfig  implements Runnable {
	private Logger logger = LoggerFactory.getLogger(TransactionKafkaConsumer.class);
	private KafkaConsumerManager kafkaConsumerManager;
	

	public TransactionKafkaConsumer(KafkaConsumerManager kafkaConsumerManager) {
		super(kafkaConsumerManager.getKafkaConfig());
		this.kafkaConsumerManager = kafkaConsumerManager;
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConsumerManager.getKafkaConfig();
	}

	public void run() {
		logger.info(" TransactionKafkaConsumer run");
		TopicType topicType = TopicType.TRANSACTION;
		List<KafkaStream<byte[], byte[]>> streamsList = getStreams(topicType);
		for(KafkaStream<byte[], byte[]>streams:streamsList){
			ConsumerIterator<byte[], byte[]> it = streams.iterator();
			 while (it.hasNext()) {
					byte[] bytes = it.next().message();
					String message = new String(bytes);
					if ( !"".equals(message.trim())) {
						post(message);
					}
			}
		}
		
	}
	
	
	private void post(String msg) {
		if(logger.isDebugEnabled()){
			logger.debug(" TransactionKafkaConsumer run message "+msg);
		}
		try {
			kafkaConsumerManager.getMessagePusher().pushTransToWeb(msg);
        } catch (Exception e) {
            logger.error(String.format("mq handle error [%s]", e));
        }
	}




}
