package com.david.ecommerceapi.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("paypal")
@Component
public class PaymentInterfacePaypal implements PaymentInterface{
    //TODO implements payment method
    private String username;
    private String password;
    @Override
    public String makePayment() {

        return "Paypal";
    }
}
