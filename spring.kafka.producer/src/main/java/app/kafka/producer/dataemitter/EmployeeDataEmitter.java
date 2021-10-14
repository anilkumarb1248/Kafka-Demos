package app.kafka.producer.dataemitter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.kafka.model.Employee;
import app.kafka.producer.sender.KafkaProducer;
import app.kafka.producer.util.EmployeesDataFileReader;

@Component
public class EmployeeDataEmitter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataEmitter.class);

	@Autowired
	private KafkaProducer kafkaProducer;

	public void sendEmployeeData() {
		LOGGER.info("Sending the employees data started ->");
		List<Employee> employeeList = EmployeesDataFileReader.getEmployeesList();
		if (!employeeList.isEmpty()) {
			employeeList.forEach(employee -> {
				kafkaProducer.sendEmployeeData(employee);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					LOGGER.error("Exception occurred whil waiting: {} ", e.getMessage());
				}
			});
		} else {
			LOGGER.info("No employees found to send to kafka");
		}

		LOGGER.info("Sending the employees data ended <-");

	}
}
