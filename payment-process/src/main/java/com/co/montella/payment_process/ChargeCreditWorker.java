package com.co.montella.payment_process;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ChargeCreditWorker {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ChargeCreditWorker.class);

    @JobWorker(type = "charge-credit-card")
    public Map<String, Double> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
        log.info("ChargeCreditWorker called{}", totalWithTax);
        return Map.of("amountCharged", totalWithTax);
    }
}
