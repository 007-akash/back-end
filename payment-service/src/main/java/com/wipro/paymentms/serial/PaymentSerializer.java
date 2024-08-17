package com.wipro.paymentms.serial;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.paymentms.entity.Payment;

public class PaymentSerializer implements Serializer<Payment>{
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration if needed
    }

    @Override
    public byte[] serialize(String topic, Payment data) {
    	if (data == null) {
            return null;
        }

    	byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
        // Cleanup if needed
    }

}
