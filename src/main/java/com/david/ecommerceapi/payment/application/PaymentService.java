package com.david.ecommerceapi.payment.application;

import com.david.ecommerceapi.payment.domain.PaymentInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final Map<String, PaymentInterface> paymentInterfaces;

    public PaymentService(List<PaymentInterface> paymentInterfaceList) {

        paymentInterfaces = paymentInterfaceList.stream()
                .collect(Collectors.toMap(x -> x.getClass().getAnnotation(Qualifier.class).value(),
                        Function.identity())
                );
    }


    public String makePayment(String paymentType) {

        return paymentInterfaces.get(paymentType).makePayment();
    }

}
