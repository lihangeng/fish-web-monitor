package com.yihuacomputer.fish.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.mq.IMqProducer;

/**
 * 
 * @author xuxiang
 *
 */
@Service
public class KafkaProducer implements IMqProducer {
	private String topic = "test";
	private String metadataBrokerList = "localhost:9092";
	private String serializerClass = "kafka.serializer.StringEncoder";
	private String requestRequiredAcks = "1";
	private ProducerConfig config;
	private Producer<String, String> producer;

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		props.put("metadata.broker.list", this.getMetadataBrokerList());
		props.put("serializer.class", this.getSerializerClass());
		props.put("request.required.acks", this.getRequestRequiredAcks());
		config = new ProducerConfig(props);
		producer = new Producer<String, String>(config);
	}

	@PreDestroy
	public void close() {
		producer.close();
	}

	@Override
	public void put(String message) {
		System.out.println("put msg is : " + message);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic,"" ,message);
		producer.send(data);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMetadataBrokerList() {
		return metadataBrokerList;
	}

	public void setMetadataBrokerList(String metadataBrokerList) {
		this.metadataBrokerList = metadataBrokerList;
	}

	public String getSerializerClass() {
		return serializerClass;
	}

	public void setSerializerClass(String serializerClass) {
		this.serializerClass = serializerClass;
	}

	public String getRequestRequiredAcks() {
		return requestRequiredAcks;
	}

	public void setRequestRequiredAcks(String requestRequiredAcks) {
		this.requestRequiredAcks = requestRequiredAcks;
	}

}
