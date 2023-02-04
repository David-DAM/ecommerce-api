package com.david.ecommerceapi.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // TODO make response of payment
    public PaymentResponse makePayment(PaymentInterface paymentInterface){
        paymentInterface.makePayment();

        return null;
    }

}
