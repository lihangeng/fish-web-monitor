package com.yihuacomputer.fish.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.mq.IMqProducer;

/**
 * kafka生产者的实现
 * @author xuxiang
 *
 */
@Service
public class KafkaProducer implements IMqProducer {

	@Autowired
	private KafkaConfig kafkaConfig;

	private ProducerConfig producerConfig;

	private Producer<String, String> producer;

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		props.put("metadata.broker.list", this.getKafkaConfig().getMetadataBrokerList());
		props.put("serializer.class", this.getKafkaConfig().getSerializerClass());
		props.put("request.required.acks", this.getKafkaConfig().getRequestRequiredAcks());
		producerConfig = new ProducerConfig(props);
		producer = new Producer<String, String>(producerConfig);
	}

	@PreDestroy
	public void close() {
		producer.close();
	}

	@Override
	public void put(String message) {
		System.out.println("put msg is : " + message);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(this.getKafkaConfig().getTopic(), "", message);
		producer.send(data);
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConfig;
	}

	public void setKafkaConfig(KafkaConfig kafkaConfig) {
		this.kafkaConfig = kafkaConfig;
	}

}
