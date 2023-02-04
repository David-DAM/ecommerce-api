package com.david.ecommerceapi.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPaypal implements PaymentInterface{
    //TODO implements payment method
    private String username;
    private String password;
    @Override
    public void makePayment() {

    }
}
