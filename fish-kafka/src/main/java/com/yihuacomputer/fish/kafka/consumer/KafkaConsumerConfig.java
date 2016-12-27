package com.yihuacomputer.fish.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.TopicType;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 根据kafka配置初始化消费者信息
 * @author GQ
 *
 */
public class KafkaConsumerConfig {

	protected int readThread = 1;
	protected KafkaConfig kafkaConfig;

	protected ConsumerConnector consumer;
	
	/**
	 * @param kafkaConfig
	 */
	public KafkaConsumerConfig(KafkaConfig kafkaConfig){
		this.kafkaConfig = kafkaConfig;
		consumer =  kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
	}
	
	protected ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", kafkaConfig.getZooKeeper());
		props.put("zookeeper.session.timeout.ms", kafkaConfig.getZookeeperSessionTimeoutMs());
		props.put("zookeeper.sync.time.ms", kafkaConfig.getZookeeperSyncTimeMs());
		props.put("auto.commit.interval.ms", kafkaConfig.getAutoCommitIntervalMs());
		setGroupId(props);
		return new ConsumerConfig(props);
	}
	protected void setGroupId(Properties props){
		props.put("group.id", kafkaConfig.getGroupId());
	}
	
	protected List<KafkaStream<byte[], byte[]>> getStreams(TopicType topicType) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicType.toString(), readThread);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topicType.toString());
		return streams;
	}
	
	/**
	 * 关闭
	 */
	public void shutdown() {
		if (consumer != null) {
			consumer.shutdown();
		}
	}
	
}
