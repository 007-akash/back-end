package com.wipro.bookingms.serializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.bookingms.model.Payment;

public class PaymentDeserializable implements Deserializer<Payment>{

private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Payment deserialize(String topic, byte[] data) {
	    if (data == null || data.length == 0) {
	        return null;
	    }
	    	
	    Payment payment = null;
		try {
			payment = (Payment)objectMapper.readValue(data, Payment.class);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return payment;
	}

}
