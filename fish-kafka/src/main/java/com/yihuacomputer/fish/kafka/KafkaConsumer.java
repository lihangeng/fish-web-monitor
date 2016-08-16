package com.yihuacomputer.fish.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpSession;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.monitor.entity.login.LoginMessage;

/**
 * kafka消费者的实现
 * 
 * @author xuxiang
 *
 */
public class KafkaConsumer implements Runnable {
	private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	private static final String STATUS = "STATUS";
	private static final String TRANS = "TRANS";
	private static final String LOGIN_IN = "LOGIN_IN";
	private static final String LOGIN_OUT = "LOGIN_OUT";
	private static final String TIME_OUT = "TIME_OUT";
	private ConsumerConnector consumer;
	private KafkaConsumerManager kafkaConsumerManager;

	public KafkaConsumer(KafkaConsumerManager kafkaConsumerManager) {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		String topic = getKafkaConfig().getTopic();
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, Integer.valueOf(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		for (final KafkaStream stream : streams) {
			logger.info("start waiting for messages ...");
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while (it.hasNext()) {
				byte[] bytes = it.next().message();
				String message = new String(bytes);
				if (message != null && !"".equals(message.trim())) {
					post(message);
				}
			}
		}
	}

	private void post(String msg) {
		 try {
            if (msg.contains(KafkaConsumer.STATUS)) {
            	kafkaConsumerManager.getMessagePusher().pushStatusToWeb(msg);
            } else if (msg.contains(KafkaConsumer.TRANS)) {
            	kafkaConsumerManager.getMessagePusher().pushTransToWeb(msg);
            }else if(msg.contains(KafkaConsumer.LOGIN_IN)){
            	 LoginMessage loginMessage=JsonUtils.fromJson(msg, LoginMessage.class);
            	 Map<String,HttpSession> mapSession = FishConstant.APPLICATION_MAP.get(loginMessage.getUsername());
            	 if(null==mapSession){
            		 mapSession = new HashMap<String,HttpSession>();
                	 mapSession.put(loginMessage.getSessionID(), null);
                	 FishConstant.APPLICATION_MAP.put(loginMessage.getUsername(), mapSession);
            	 }
            }else if(msg.contains(KafkaConsumer.LOGIN_OUT)){
            	 LoginMessage loginMessage=JsonUtils.fromJson(msg, LoginMessage.class);
            	 Map<String,HttpSession> mapSession =FishConstant.APPLICATION_MAP.get(loginMessage.getUsername());
             	 if(mapSession!=null){
             		 HttpSession session = mapSession.get(loginMessage.getSessionID());
            		 if(session!=null){
            			 session.invalidate();
            		 }
            	 }	
            	 FishConstant.APPLICATION_MAP.remove(loginMessage.getUsername());
            }else if(msg.contains(KafkaConsumer.TIME_OUT)){
           	 LoginMessage loginMessage=JsonUtils.fromJson(msg, LoginMessage.class);
           	 Map<String,HttpSession> mapSession =FishConstant.APPLICATION_MAP.get(loginMessage.getUsername());
            	 if(mapSession!=null){
            		 HttpSession session = mapSession.get(loginMessage.getSessionID());
           		 if(session!=null){
           			 session.invalidate();
           		 }
           	 }	
           	 FishConstant.APPLICATION_MAP.remove(loginMessage.getUsername());
           }
            
            else{
            	logger.warn("ignore message [%s]",msg);
            }
        } catch (Exception e) {
            logger.error("mq handle error [%s]", e);
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
