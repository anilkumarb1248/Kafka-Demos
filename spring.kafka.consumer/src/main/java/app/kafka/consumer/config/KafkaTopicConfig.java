package app.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public KafkaAdmin admin() {
	    Map<String, Object> configs = new HashMap<>();
	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    return new KafkaAdmin(configs);
	}
	
	@Bean
	public NewTopic textDataTopic() {
		return TopicBuilder
				.name("text-data-topic")
				.partitions(10)
				.replicas(3)
				.compact()
				.build();
	}
	
	@Bean
	public NewTopic employeeDataTopic() {
		return TopicBuilder
				.name("employee-data-topic")
				.partitions(10)
				.replicas(3)
				.compact()
				.build();
	}
	
	@Bean
	public NewTopic employeesListTopic() {
		return TopicBuilder
				.name("employees-list-topic")
				.partitions(10)
				.replicas(3)
				.compact()
				.build();
	}

}
