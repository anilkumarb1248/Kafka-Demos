package app.kafka.producer.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.kafka.model.Employee;

public class EmployeesDataFileReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesDataFileReader.class);

	
	public static List<Employee> getEmployeesList() {
		LOGGER.info("Reading the Employees data from the file");
		List<Employee> employeesList = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Resource resource = new ClassPathResource("employees-data.json");
			InputStream inputStream = resource.getInputStream();
			TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {
			};

			employeesList = mapper.readValue(inputStream, typeReference);

		} catch (Exception e) {
			LOGGER.info("Failed to read the employees data from the file");
			LOGGER.error(e.getMessage());
			employeesList = new ArrayList<Employee>();
		}
		return employeesList;
	}

}
