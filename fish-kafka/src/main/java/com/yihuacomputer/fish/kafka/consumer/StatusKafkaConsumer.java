package com.yihuacomputer.fish.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

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
public class StatusKafkaConsumer implements Runnable {
	private Logger logger = LoggerFactory.getLogger(StatusKafkaConsumer.class);
	private ConsumerConnector consumer;
	private KafkaConsumerManager kafkaConsumerManager;
	
	private int readThread = 1;

	public StatusKafkaConsumer(KafkaConsumerManager kafkaConsumerManager) {
		this.kafkaConsumerManager = kafkaConsumerManager;
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConsumerManager.getKafkaConfig();
	}

	public void shutdown() {
		if (consumer != null) {
			consumer.shutdown();
		}
	}

	public void run() {
		logger.info(" StatusKafkaConsumer run");
		//状态消费
		TopicType topicType = TopicType.STATUS;

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicType.toString(), readThread);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streamsList = consumerMap.get(topicType.toString());
		for(final KafkaStream<byte[], byte[]>streams:streamsList){
			 ConsumerIterator<byte[], byte[]> it = streams.iterator();
			 while (it.hasNext()) {
				byte[] bytes = it.next().message();
				String message = new String(bytes);
				if (message != null && !"".equals(message.trim())) {
					if(logger.isDebugEnabled()){
						logger.debug(String.format("mq info is will push to Web [%s]", message));
					}
					try {
						kafkaConsumerManager.getMessagePusher().pushStatusToWeb(message);
			        } catch (Exception e) {
			            logger.error(String.format("mq handle error [%s]", e.getMessage()));
			        }
				}
			}
		}
	}
	


	private ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", getKafkaConfig().getZooKeeper());
		props.put("group.id", getKafkaConfig().getGroupId());
		props.put("zookeeper.session.timeout.ms", getKafkaConfig().getZookeeperSessionTimeoutMs());
		props.put("zookeeper.sync.time.ms", getKafkaConfig().getZookeeperSyncTimeMs());
		props.put("auto.commit.interval.ms", getKafkaConfig().getAutoCommitIntervalMs());
		return new ConsumerConfig(props);
	}


}
