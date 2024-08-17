package com.wipro.paymentms.serial;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.paymentms.model.Booking;

public class BookingDeserializer implements Deserializer<Booking>{
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Booking deserialize(String topic, byte[] data) {
	    if (data == null || data.length == 0) {
	        return null;
	    }
	    	
	    Booking boking = null;
		try {
			boking = (Booking)objectMapper.readValue(data, Booking.class);
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

	    return boking;
	}

}
