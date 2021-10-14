package app.kafka.producer.dataemitter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.kafka.model.Employee;
import app.kafka.model.EmployeeList;
import app.kafka.producer.sender.KafkaProducer;
import app.kafka.producer.util.EmployeesDataFileReader;

@Component
public class EmployeesListEmitter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesListEmitter.class);

	@Autowired
	private KafkaProducer kafkaProducer;

	public void sendEmployeesList() {
		LOGGER.info("Sending the employees list started ->");
		List<Employee> list = EmployeesDataFileReader.getEmployeesList();
		if (!list.isEmpty()) {
			EmployeeList employeeList = new EmployeeList();
			employeeList.setEmployeeList(list);
			kafkaProducer.sendEmployeesList(employeeList);

		} else {
			LOGGER.info("No employees found to send to kafka");
		}
		LOGGER.info("Sending the employees list ended <-");
	}
}
