package app.kafka.producer.dataemitter;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import app.kafka.producer.sender.KafkaProducer;

@Component
public class TextDataEmitter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TextDataEmitter.class);

	private boolean exitFlag;

	@Autowired
	private KafkaProducer kafkaProducer;

	public void sendTextData(Scanner scanner) {
		LOGGER.info("Taking user input started ->");
		exitFlag = false;
		String data = null;
		while (!exitFlag) {
			System.out.println("Enter data which you want to send to kafka, If you want exit enter 'exit'");
			data = scanner.nextLine();
			System.out.println("Entered text: " + data);
			if (data.equalsIgnoreCase("exit")) {
				exitFlag = true;
				data = null;
				break;
			}
			if (StringUtils.hasText(data)) {
				kafkaProducer.sendTextData(data);
			}
			data = null;
		}
		LOGGER.info("Taking user input ended <-");
	}
}
