package com.yihuacomputer.fish.kafka.consumer;

import java.util.List;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.KafkaConsumerManager;
import com.yihuacomputer.fish.kafka.TopicType;

/**
 * kafka消费者的实现
 * 
 * @author xuxiang
 *
 */
public class StatusKafkaConsumer  extends KafkaConsumerConfig implements Runnable {
	private Logger logger = LoggerFactory.getLogger(StatusKafkaConsumer.class);
	private KafkaConsumerManager kafkaConsumerManager;
	

	public StatusKafkaConsumer(KafkaConsumerManager kafkaConsumerManager) {
		super(kafkaConsumerManager.getKafkaConfig());
		this.kafkaConsumerManager = kafkaConsumerManager;
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConsumerManager.getKafkaConfig();
	}

	public void run() {
		logger.info(" StatusKafkaConsumer run");
		//状态消费
		TopicType topicType = TopicType.STATUS;

		List<KafkaStream<byte[], byte[]>> streamsList =  getStreams(topicType);
		for(final KafkaStream<byte[], byte[]>streams:streamsList){
			 ConsumerIterator<byte[], byte[]> it = streams.iterator();
			 while (it.hasNext()) {
				byte[] bytes = it.next().message();
				String message = new String(bytes);
				if ( !"".equals(message.trim())) {
					if(logger.isDebugEnabled()){
						logger.debug(String.format("mq info is will push to Web [%s]", message));
					}
					try {
						kafkaConsumerManager.getMessagePusher().pushStatusToWeb(message);
			        } catch (Exception e) {
			            logger.error(String.format("mq handle error [%s]", e));
			        }
				}
			}
		}
	}

}
