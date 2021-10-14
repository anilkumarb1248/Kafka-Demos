package app.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import app.kafka.model.Employee;
import app.kafka.model.EmployeeList;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "text-data-topic", groupId = "text-data", containerFactory = "text-kafka-listener")
	public void consumeTextData(String data) {
		System.out.println("Data: " + data);
	}

	@KafkaListener(topics = "employee-data-topic", groupId = "employee-data", containerFactory = "employee-data-kafka-listener")
	public void consumeEmployeeData(Employee employee) {
		System.out.println("Employee: " + employee);
	}

	@KafkaListener(topics = "employees-list-topic", groupId = "employees-list", containerFactory = "employees-list-kafka-listener")
	public void consumeEmployeesList(EmployeeList employeeList) {
		System.out.println("EmployeeList: " + employeeList);
	}

}
