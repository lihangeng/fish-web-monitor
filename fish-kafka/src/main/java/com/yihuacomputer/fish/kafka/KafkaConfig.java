package com.yihuacomputer.fish.kafka;

import org.springframework.stereotype.Service;

@Service
public class KafkaConfig {
	private String topic = "test";
	private String zooKeeper = "127.0.0.1:2181";
	private String groupId = "test-consumer-group";

	public String getZooKeeper() {
		return zooKeeper;
	}

	public void setZooKeeper(String zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTopic() {
		return topic;
	}

}
