package com.yihuacomputer.fish.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

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
		logger.info(String.format("put msg is : [%s] " , message));
	}

	public KafkaConfig getKafkaConfig() {
		return kafkaConfig;
	}

	public void setKafkaConfig(KafkaConfig kafkaConfig) {
		this.kafkaConfig = kafkaConfig;
	}

	@Override
	public void putStatus(String statusMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("put statusMsg is : [%s] " , statusMessage));
		}
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicType.STATUS.toString(), "", statusMessage);
		producer.send(data);
	}

	@Override
	public void putTransaction(String transMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("put transMessage is : [%s] " , transMessage));
		}
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicType.TRANSACTION.toString(), "", transMessage);
		producer.send(data);
	}

	@Override
	public void putLogin(String loginMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("put loginMessage is : [%s] " , loginMessage));
		}
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicType.LOGIN.toString(), "", loginMessage);
		producer.send(data);
	}

	@Override
	public void putLogout(String logoutMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("put logoutMessage is : [%s] " , logoutMessage));
		}
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicType.LOGOUT.toString(), "", logoutMessage);
		producer.send(data);
	}
	
	@Override
	public void putCaseFault(String caseFault) {
		if(logger.isDebugEnabled()){
			logger.debug(String.format("put caseFaultMsg is : [%s] " , caseFault));
		}
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicType.CASEFAULT.toString(), "", caseFault);
		producer.send(data);
	}

}
