package app.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApp implements ApplicationRunner {
	
	@Autowired
	MultpleProducersHandler multpleProducersHandler;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApp.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		multpleProducersHandler.sendEventsTokafka();
	}

}
