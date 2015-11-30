package com.yihuacomputer.fish.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

@SuppressWarnings("rawtypes")
public class KafkaConsumerThread  implements Runnable {
    
	private KafkaStream m_stream;
    private int m_threadNumber;
 
    public KafkaConsumerThread(KafkaStream a_stream, int a_threadNumber) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
    }
 
    @SuppressWarnings("unchecked")
	public void run() {
    	System.out.println("xxxx");
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()){
            System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}
