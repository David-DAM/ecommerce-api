package com.david.ecommerceapi.payment;

import java.util.Date;
//TODO implements payment method
public class PaymentCreditCard implements PaymentInterface{
    private String number;
    private Date expirationDate;
    private String ccv;
    @Override
    public void makePayment() {

    }
}
