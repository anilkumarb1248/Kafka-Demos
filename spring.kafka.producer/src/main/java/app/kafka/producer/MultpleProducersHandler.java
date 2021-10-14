package app.kafka.producer;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.kafka.producer.dataemitter.EmployeeDataEmitter;
import app.kafka.producer.dataemitter.EmployeesListEmitter;
import app.kafka.producer.dataemitter.TextDataEmitter;

@Component
public class MultpleProducersHandler {
	
	Scanner scanner = new Scanner(System.in);
	
	@Autowired
	private TextDataEmitter textDataEmitter;

	@Autowired
	private EmployeeDataEmitter employeeDataEmitter;

	@Autowired
	private EmployeesListEmitter employeesListEmitter;

	public void sendEventsTokafka() {
		int choice = 0;
		while((choice = selectChoice()) != 4) {
			switch (choice) {
				case 1:
					sendTextData();
					break;
				case 2:
					sendEmployeeData();
					break;
				case 3:
					sendEmployeesList();
					break;
				case 4:
					System.out.println("Thank you!");
					break;
	
				default:
					choice = 4;
					System.out.println("Wrong choice");
					break;
			}
		}

	}
	
	public int selectChoice() {
		System.out.println("--- Available choices --- \n"
				+ "1. To send user input \n"
				+ "2. To send employee details \n"
				+ "3. To send employees list at once \n"
				+ "4. Exit \n"
				);
		System.out.println("Enter your choice");
		
		int choice = 0;
		choice = scanner.nextInt();
		
		// We can not open the Scanner in multiple places in single App
//		try(Scanner scanner = new Scanner(System.in)) {
//			choice = scanner.nextInt();
//		}catch(Exception e) {
//			System.out.println("Exception in choice selection: " + e.getMessage());
//		}
		return choice;
	}

	public void sendTextData() {
		// To send some user input messages to kafka
		textDataEmitter.sendTextData(scanner);
	}

	public void sendEmployeeData() {
		// To send employee details
		employeeDataEmitter.sendEmployeeData();
	}

	public void sendEmployeesList() {
		// To send all employees at once as a list
		employeesListEmitter.sendEmployeesList();
	}
	
	@Override
	protected void finalize() throws Throwable {
		scanner.close();
		super.finalize();
	}

}
