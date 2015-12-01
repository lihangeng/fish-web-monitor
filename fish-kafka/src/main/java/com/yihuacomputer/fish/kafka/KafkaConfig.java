package com.yihuacomputer.fish.kafka;


public class KafkaConfig {
	/**公用配置**/
	private String topic = "atmv";
	/**
	 * 生产者配置
	 */
	private String metadataBrokerList = "localhost:9092";
	private String serializerClass = "kafka.serializer.StringEncoder";
	private String requestRequiredAcks = "1";
	/**
	 * 消费者配置
	 */
	private String zooKeeper = "127.0.0.1:2181";
	private String groupId = "test-group";
	private String zookeeperSessionTimeoutMs = "3000";
	private String zookeeperSyncTimeMs = "200";
	private String autoCommitIntervalMs = "1000";

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

	public String getZookeeperSessionTimeoutMs() {
		return zookeeperSessionTimeoutMs;
	}

	public void setZookeeperSessionTimeoutMs(String zookeeperSessionTimeoutMs) {
		this.zookeeperSessionTimeoutMs = zookeeperSessionTimeoutMs;
	}

	public String getZookeeperSyncTimeMs() {
		return zookeeperSyncTimeMs;
	}

	public void setZookeeperSyncTimeMs(String zookeeperSyncTimeMs) {
		this.zookeeperSyncTimeMs = zookeeperSyncTimeMs;
	}

	public String getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}

	public void setAutoCommitIntervalMs(String autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
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
