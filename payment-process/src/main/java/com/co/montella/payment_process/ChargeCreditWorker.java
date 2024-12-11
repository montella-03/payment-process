package com.co.montella.payment_process;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;


@Component
@Slf4j
public class ChargeCreditWorker {

    @JobWorker(type = "charge-credit-card")
    public Map<String, Double> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
        log.info("ChargeCreditWorker called{}", totalWithTax);

        BigDecimal total = new BigDecimal(totalWithTax);

        //consider msg
        total = total.multiply(new BigDecimal("0.9"));
        totalWithTax = total.doubleValue();

        return Map.of("amountCharged", totalWithTax);
    }
}
