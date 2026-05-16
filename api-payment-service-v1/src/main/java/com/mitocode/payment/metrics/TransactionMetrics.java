package com.mitocode.payment.metrics;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMetrics {

    private final DistributionSummary chargedAmount;
    private final DistributionSummary refundedAmount;

    public TransactionMetrics(MeterRegistry registry) {
        this.chargedAmount = DistributionSummary.builder("transaction_charged_amount")
                .description("Distribution of charged amounts")
                .baseUnit("PEN")
                .register(registry);

        this.refundedAmount = DistributionSummary.builder("transaction_refunded_amount")
                .description("Distribution of refunded amounts")
                .baseUnit("PEN")
                .register(registry);
    }

    public void recordChargedAmount(BigDecimal amount) {
        chargedAmount.record(amount.doubleValue());
    }

    public void recordRefundedAmount(BigDecimal amount) {
        refundedAmount.record(amount.doubleValue());
    }

}
