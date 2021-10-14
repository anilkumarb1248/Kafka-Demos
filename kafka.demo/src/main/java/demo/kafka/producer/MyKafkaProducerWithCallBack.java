package demo.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyKafkaProducerWithCallBack {

	private final static String BOOTSTRAP_SERVERS = "localhost:9092";
	private final static String TOPIC_NAME = "MyFirstTopic1";

	// Method to create producer object
	public static KafkaProducer<String, String> createProducer() {
		// Create Configuration properties object
		Properties properties = new Properties();
		// Kafka broker topic details.
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "my-kafka-producer");

		// Serialization class details
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// Creating a producer
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
		return kafkaProducer;
	}

	public static void main(String[] args) throws InterruptedException {

		final KafkaProducer<String, String> kafkaProducer = createProducer();
		for (int i = 1; i <= 5; i++) {
			String key = "ID_" + i;
			String message = "Dummy Message_" + i;
			ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, key, message);
			kafkaProducer.send(producerRecord, new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e == null) {
						System.out.println("*** Successfully received the details");
						System.out.println(" Topic: " + metadata.topic() + "\n" 
						+ " Partition: " + metadata.partition() + "\n" 
						+ " Offset: " + metadata.offset());
					} else {
						System.err.println("Cannot produce, getting exception: " + e.getMessage());
					}

				}
			});
			kafkaProducer.flush();
			Thread.sleep(5000);
		}
		kafkaProducer.close();
	}

}
