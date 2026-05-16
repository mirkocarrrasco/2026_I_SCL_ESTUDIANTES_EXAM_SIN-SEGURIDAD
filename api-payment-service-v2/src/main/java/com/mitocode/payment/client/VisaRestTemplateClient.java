package com.mitocode.payment.client;

import com.mitocode.payment.client.dto.ChargeRequest;
import com.mitocode.payment.client.dto.ChargeResponse;
import com.mitocode.payment.client.dto.CheckBalanceRequest;
import com.mitocode.payment.client.dto.CheckBalanceResponse;
import com.mitocode.payment.error.ChargeErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@Slf4j
@Component
public class VisaRestTemplateClient {

    private final String baseUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public VisaRestTemplateClient(
            //http://localhost:52010/api/visa
            @Value("${http-clients.external.api-visa-v1.base-url}")
                                  String baseUrl,
                                  RestTemplate restTemplate,
                                  ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public CheckBalanceResponse checkBalance(Long cardId, BigDecimal requiredAmount) {
        CheckBalanceRequest request = new CheckBalanceRequest(cardId.toString(), requiredAmount);

        return restTemplate.postForObject(baseUrl + "/account/check-balance", request, CheckBalanceResponse.class);
    }

    public ChargeResponse charge(Long cardId, BigDecimal amount) {
        ChargeRequest request = new ChargeRequest(cardId.toString(), amount);

        //Forma simple
        //return restTemplate.postForObject(baseUrl + "/charge", request, ChargeResponse.class);

        try {
            ResponseEntity<ChargeResponse> response = restTemplate.exchange(baseUrl + "/account/charge",
                    HttpMethod.POST,
                    new HttpEntity<>(request),
                    ChargeResponse.class);

            return response.getBody();

        } catch (HttpClientErrorException | HttpServerErrorException ex) {

            HttpStatusCode status = ex.getStatusCode();
            String body = ex.getResponseBodyAsString();

            ChargeResponse chargeResponse;

            try {
                chargeResponse = objectMapper.readValue(body, ChargeResponse.class);
            } catch (Exception mapperException) {
                throw new RuntimeException(mapperException.getMessage(), mapperException.getCause());
            }

            log.error("Error status: {}, body: {}", status, body);

            throw new ChargeErrorException("Error from VISA API: [status = " + status + "]", chargeResponse.status(), status.value());
        }
    }
}
