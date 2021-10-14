package app.kafka.producer.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import app.kafka.model.Employee;
import app.kafka.model.EmployeeList;

@Service
public class KafkaProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	@Qualifier("text-data-kafka-template")
	KafkaTemplate<String, String> textDataKafkaTemplate;

	@Autowired
	@Qualifier("employee-data-kafka-template")
	KafkaTemplate<String, Employee> employeeDataKafkaTemplate;
	
	@Autowired
	@Qualifier("employees-list-kafka-template")
	KafkaTemplate<String, EmployeeList> employeesListKafkaTemplate;

	public void sendTextData(String data) {
		LOGGER.info("Sending text data to kafka: {}", data);
		textDataKafkaTemplate.send("text-data-topic", data);
	}

	public void sendEmployeeData(Employee employee) {
		LOGGER.info("Sending employee data to kafka");
		employeeDataKafkaTemplate.send("employee-data-topic", employee);
	}
	
	public void sendEmployeesList(EmployeeList employeeList) {
		LOGGER.info("Sending employees list to kafka");
		employeesListKafkaTemplate.send("employees-list-topic", employeeList);
	}

}
