package com.hackathon.vault.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * Used to serialize Java.util.Date, which is not a common JSON type, so
 *         we have to create a custom serialize method;.e.g. 2015-05-29T11:11:57Z
 *                                                           
 *
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

  private static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final String UTC_TIME_ZONE = "UTC";

  // Define the format
  private SimpleDateFormat formatter = new SimpleDateFormat(JSON_DATE_FORMAT);

  /**
   * This method helps to serialize the dates into the customized format
   */
  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
    throws IOException, JsonProcessingException {
    formatter.setTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE));
    formatter.setLenient(false);
    gen.writeString(formatter.format(value));
  }
}
