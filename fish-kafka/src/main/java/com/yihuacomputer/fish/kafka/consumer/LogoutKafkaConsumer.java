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

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.api.session.LoginMessage;
import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.KafkaConsumerManager;
import com.yihuacomputer.fish.kafka.TopicType;

public class LogoutKafkaConsumer implements Runnable {

	private Logger logger = LoggerFactory.getLogger(LogoutKafkaConsumer.class);
	private ConsumerConnector consumer;
	private KafkaConsumerManager kafkaConsumerManager;
	private ISessionManage sessionManage;

	private int readThread = 1;

	public LogoutKafkaConsumer(KafkaConsumerManager kafkaConsumerManager, ISessionManage sessionManage) {
		this.kafkaConsumerManager = kafkaConsumerManager;
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.sessionManage = sessionManage;
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
		logger.warn(" LogoutKafkaConsumer run");
		TopicType topicType = TopicType.LOGOUT;
		List<KafkaStream<byte[], byte[]>> streamsList = getStreams(topicType);
		for(KafkaStream<byte[], byte[]>streams:streamsList){
			 ConsumerIterator<byte[], byte[]> it = streams.iterator();
			 while (it.hasNext()) {
					byte[] bytes = it.next().message();
					String message = new String(bytes);
					if (message != null && !"".equals(message.trim())) {
						post(message);
					}
			}
		}
	}

	private List<KafkaStream<byte[], byte[]>> getStreams(TopicType topicType) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicType.toString(), readThread);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topicType.toString());
		return streams;
	}

	private void post(String msg) {
		logger.info(String.format("mq info [%s]", msg));
		try {
			LoginMessage loginMessage = JsonUtils.fromJson(msg, LoginMessage.class);
			sessionManage.logoutByNotice(loginMessage);
		} catch (Exception e) {
			logger.error(String.format("mq handle error [%s]", e.getMessage()));
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
