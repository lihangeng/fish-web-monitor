package com.yihuacomputer.fish.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PreDestroy;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer implements Runnable{
	private ConsumerConnector consumer;
	private KafkaConsumerManager kafkaConsumerManager;
	
	public KafkaConsumer(KafkaConsumerManager kafkaConsumerManager){
		this.kafkaConsumerManager = kafkaConsumerManager;
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(getKafkaConfig().getZooKeeper(), getKafkaConfig().getGroupId()));
	}
	
	public KafkaConfig getKafkaConfig() {
		return kafkaConsumerManager.getKafkaConfig();
	}
	
	@PreDestroy
	public void shutdown() {
		if (consumer != null){
			consumer.shutdown();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		String topic = getKafkaConfig().getTopic();
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		while(true){
			List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
			for (final KafkaStream stream : streams) {
				ConsumerIterator<byte[], byte[]> it = stream.iterator();
		        while (it.hasNext()){
		            String message = new String(it.next().message());
		            System.out.println(message);
		            if(message != null && !"".equals(message.trim())){
		            	kafkaConsumerManager.getMessagePusher().pushStatusToWeb(message);
		            }
		        }
			}
		}
	}
	private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
		Properties props = new Properties();
		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", "3000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		return new ConsumerConfig(props);
	}

}
