package com.david.ecommerceapi.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Qualifier("card")
@Component
public class PaymentInterfaceCard implements PaymentInterface{
    private String number;
    private Date expirationDate;
    private String ccv;
    @Override
    public String makePayment() {
        return "Credit card";
    }
}
