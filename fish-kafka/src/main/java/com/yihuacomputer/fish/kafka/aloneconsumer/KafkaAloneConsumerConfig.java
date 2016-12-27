package com.yihuacomputer.fish.kafka.aloneconsumer;

import java.util.Properties;

import com.yihuacomputer.fish.kafka.KafkaConfig;
import com.yihuacomputer.fish.kafka.consumer.KafkaConsumerConfig;


/**
 * 独立消费的kafka配置,主要设置组信息
 * @author GQ
 *
 */
public class KafkaAloneConsumerConfig extends KafkaConsumerConfig{
	
	/**
	 * @param kafkaConfig
	 */
	public KafkaAloneConsumerConfig(KafkaConfig kafkaConfig){
		super(kafkaConfig);
	}
	protected void setGroupId(Properties props){
		props.put("group.id", "alone");
	}
	
}
