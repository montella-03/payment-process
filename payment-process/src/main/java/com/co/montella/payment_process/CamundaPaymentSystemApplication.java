package com.co.montella.payment_process;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@Deployment(resources = "classpath:process-payments.bpmn")
public class CamundaPaymentSystemApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CamundaPaymentSystemApplication.class);
	private final ZeebeClient client;

    public CamundaPaymentSystemApplication(ZeebeClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
		SpringApplication.run(CamundaPaymentSystemApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		var bpmnProcessId = "process-payments";
		var event = client.newCreateInstanceCommand()
				.bpmnProcessId(bpmnProcessId)
				.latestVersion()
				.variables(Map.of("total", 100))
				.send()
				.join();
		log.info("started a process: {}", Optional.of(event.getProcessInstanceKey()));
	}
}
