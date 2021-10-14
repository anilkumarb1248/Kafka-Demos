package app.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import app.kafka.model.Employee;
import app.kafka.model.EmployeeList;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	// Text Consumer KafkaTemplate configuration -->
	@Bean("text-kafka-listener")
	public ConcurrentKafkaListenerContainerFactory<String, String> textDataKafkaListener() {
		ConcurrentKafkaListenerContainerFactory<String, String> textDataFactory = new ConcurrentKafkaListenerContainerFactory<>();
		textDataFactory.setConsumerFactory(textDataConsumerFactory());
		return textDataFactory;
	}

	@Bean
	public ConsumerFactory<String, String> textDataConsumerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "text-data");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	// Text Consumer KafkaTemplate configuration <--

	// Employee details Consumer KafkaTemplate configuration -->
	@Bean("employee-data-kafka-listener")
	public ConcurrentKafkaListenerContainerFactory<String, Employee> employeeDataKafkaListener() {
		ConcurrentKafkaListenerContainerFactory<String, Employee> employeeDataFactory = new ConcurrentKafkaListenerContainerFactory<>();
		employeeDataFactory.setConsumerFactory(employeeDataConsumerFactory());
		return employeeDataFactory;
	}

	@Bean
	public ConsumerFactory<String, Employee> employeeDataConsumerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "employee-data");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<Employee> deserializer = new JsonDeserializer<Employee>(Employee.class);
		deserializer.addTrustedPackages("*");

		return new DefaultKafkaConsumerFactory<String, Employee>(config, new StringDeserializer(), deserializer);

	}
	// Employee details Consumer KafkaTemplate configuration <--

	// Employees list Consumer KafkaTemplate configuration -->
	@Bean("employees-list-kafka-listener")
	public ConcurrentKafkaListenerContainerFactory<String, EmployeeList> employeesListKafkaListener() {
		ConcurrentKafkaListenerContainerFactory<String, EmployeeList> employeeListFactory = new ConcurrentKafkaListenerContainerFactory<>();
		employeeListFactory.setConsumerFactory(employeesListConsumerFactory());
		return employeeListFactory;
	}

	@Bean
	public ConsumerFactory<String, EmployeeList> employeesListConsumerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "employees-list");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<EmployeeList> deserializer = new JsonDeserializer<EmployeeList>(EmployeeList.class);
		deserializer.addTrustedPackages("*");

		return new DefaultKafkaConsumerFactory<String, EmployeeList>(config, new StringDeserializer(), deserializer);
	}
	// Employees list Consumer KafkaTemplate configuration <--

}
