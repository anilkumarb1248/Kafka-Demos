package demo.kafka.consumer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class MyKafkaConsumer {
//	private static Logger LOGGER = LoggerFactory.getLogger(MyKafkaConsumer.class.getName());

	private final static String BOOTSTRAP_SERVERS = "localhost:9092";
	private final static String TOPIC_NAME = "MyFirstTopic1";
	private final static String GROUP_ID = "consumer-app";
	private final static String OFFSET_RESET_CONFIG = "earliest";

	private static KafkaConsumer<String, String> kafkaConsumer = null;

	// Method to create consumer object
	public static KafkaConsumer<String, String> createConsumer() {
		// Create Configuration properties object
		Properties properties = new Properties();
		// Kafka broker topic details.
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_CONFIG);

		// DeSerialization class details
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		return new KafkaConsumer<>(properties);
	}

	public static void main(String[] args) {
		kafkaConsumer = createConsumer();
		kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));

		System.out.println("-- Consumer App is started and waiting for the messages: ");
		// polling
		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("***** New Message Arrived at:  " + LocalTime.now());
				System.out.println("  Key: " + record.key() + ", Value:" + record.value());
				System.out.println("  Partition:" + record.partition() + ",Offset:" + record.offset());
				System.out.println("  Total record: " + record);
			}
		}

	}

	@Override
	protected void finalize() throws Throwable {
		if (kafkaConsumer != null) {
			kafkaConsumer.close();
		}
		super.finalize();
	}

}
