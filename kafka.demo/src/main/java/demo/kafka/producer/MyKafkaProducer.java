package demo.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyKafkaProducer {

	private final static String BOOTSTRAP_SERVERS = "localhost:9092";
	private final static String TOPIC_NAME = "MyFirstTopic1";

	// Method to create producer object
	public static KafkaProducer<String, String> createProducer() {
		// Create Configuration properties object
		Properties properties = new Properties();
		// Kafka broker topic details.
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "my-kafka-producer");

		// Serialization class details
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// Creating a producer
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
		return kafkaProducer;
	}

	public static void main(String[] args) throws InterruptedException {

		final KafkaProducer<String, String> kafkaProducer = createProducer();
		// Sending single Message
//		ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "First Message from Java 123");
//		kafkaProducer.send(producerRecord);
//		kafkaProducer.flush();

		// Sending Multiple Messages
		for (int i = 1; i <= 10; i++) {
			String key = "ID_" + i;
			String message = "Dummy Message_" + i;
			ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, key, message);
			kafkaProducer.send(producerRecord);
			kafkaProducer.flush();
			Thread.sleep(5000);
		}
		kafkaProducer.close();
	}

}
