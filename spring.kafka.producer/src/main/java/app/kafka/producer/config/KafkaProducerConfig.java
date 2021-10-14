package app.kafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import app.kafka.model.Employee;
import app.kafka.model.EmployeeList;

@Configuration
public class KafkaProducerConfig {

	private final static String BOOTSTRAP_SERVERS = "localhost:9092";

	// Text producer KafkaTemplate configuration -->
	@Bean("text-data-kafka-template")
	public KafkaTemplate<String, String> textDataKafkaTemplate() {
		return new KafkaTemplate<>(textDataProducerFactory());
	}

	@Bean
	public ProducerFactory<String, String> textDataProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	// Text producer KafkaTemplate configuration <--

	// Employee details producer KafkaTemplate configuration -->
	@Bean("employee-data-kafka-template")
	public KafkaTemplate<String, Employee> employeeDataKafkaTemplate() {
		return new KafkaTemplate<>(employeeProducerFactory());
	}

	@Bean
	public ProducerFactory<String, Employee> employeeProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<Employee>());
	}

	// Employee details producer KafkaTemplate configuration <--

	// Employees list producer KafkaTemplate configuration -->
	@Bean("employees-list-kafka-template")
	public KafkaTemplate<String, EmployeeList> employeesListKafkaTemplate() {
		return new KafkaTemplate<>(employeesListProducerFactory());
	}

	@Bean
	public ProducerFactory<String, EmployeeList> employeesListProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<EmployeeList>());
	}
	// Employees list producer KafkaTemplate configuration <--

}
