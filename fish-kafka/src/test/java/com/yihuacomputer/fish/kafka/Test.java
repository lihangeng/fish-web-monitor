package com.yihuacomputer.fish.kafka;

import com.yihuacomputer.fish.api.mq.IMessagePusher;


public class Test {

	public static void main(String[] args) {
//		testProducer();
		testConsumer();

	}

	private static void testConsumer() {
		KafkaConfig config = new KafkaConfig();
		
		KafkaConsumerManager manager = new KafkaConsumerManager();
		manager.setKafkaConfig(config);
		
		IMessagePusher messagePusher = new IMessagePusher(){

			@Override
			public void pushStatusToWeb(String message) {
				System.out.println("status is " + message);
			}

			@Override
			public void pushTransToWeb(String message) {
				System.out.println("trans is " + message);
			}
			
		};
		
		manager.setMessagePusher(messagePusher);
		
		manager.init();
		
		
		
	}

	private static void testProducer() {
		KafkaProducer producer = new KafkaProducer();
		producer.init();
		
		int i = 0;
		while(i < 10){
			producer.put("hello " + i);
			i++;
		}
		
		producer.close();
	}

}
