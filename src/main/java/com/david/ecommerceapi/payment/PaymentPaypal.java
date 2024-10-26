package com.david.ecommerceapi.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("paypal")
@Component
public class PaymentPaypal implements PaymentInterface{

    private String username;
    private String password;
    @Override
    public String makePayment() {

        return "Paypal";
    }
}
