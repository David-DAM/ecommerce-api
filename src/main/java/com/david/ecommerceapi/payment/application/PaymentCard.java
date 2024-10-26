package com.david.ecommerceapi.payment.application;

import com.david.ecommerceapi.payment.domain.PaymentInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Qualifier("card")
@Component
public class PaymentCard implements PaymentInterface {
    private String number;
    private Date expirationDate;
    private String ccv;

    @Override
    public String makePayment() {
        return "Credit card";
    }
}
