package com.mitocode.payment.service;

import com.mitocode.payment.client.VisaRestTemplateClient;
import com.mitocode.payment.metrics.TransactionMetrics;
import com.mitocode.payment.producer.payment.completed.PaymentCompletedProducer;
import com.mitocode.payment.domain.Charge;
import com.mitocode.payment.producer.payment.failed.PaymentFailedProducer;
import com.mitocode.payment.repository.ChargeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private static final String COMPLETED_STATUS = "COMPLETED";

    private final ChargeRepository chargeRepository;
    private final VisaRestTemplateClient visaClient;
    private final PaymentCompletedProducer paymentCompletedProducer;
    private final PaymentFailedProducer paymentFailedProducer;
    private final TransactionMetrics transactionMetrics;

    public void charge(UUID orderId, Charge charge) {

        try {
            visaClient.charge(charge.getCardId(), charge.getAmount());

            Charge chargeMutated = charge.toBuilder().status(COMPLETED_STATUS).build();

            chargeRepository.save(chargeMutated);
            transactionMetrics.recordChargedAmount(chargeMutated.getAmount());
            paymentCompletedProducer.produce(orderId.toString(), chargeMutated);
        } catch (Exception ex) {
            paymentFailedProducer.produce(orderId.toString(), charge, ex.getMessage());
            throw ex;
        }

    }

}
