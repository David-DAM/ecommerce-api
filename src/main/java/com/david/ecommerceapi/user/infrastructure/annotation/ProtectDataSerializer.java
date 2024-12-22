package com.david.ecommerceapi.user.infrastructure.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProtectDataSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String masked = data.replaceAll("\\w(?=\\w{4})", "x");

        jsonGenerator.writeString(masked);
    }
}
