package com.hackathon.vault.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * This class does the DeSerialization of  Java.util.Date, as per the custom format
 *
 */
@Component
public class JsonDateDeserializer extends JsonDeserializer<Date> {

  private static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final String UTC_TIME_ZONE = "UTC";

  // Define the format
  private SimpleDateFormat formatter = new SimpleDateFormat(
    JSON_DATE_FORMAT);

  /**
   * Deserialization method
   */
  @Override
  public Date deserialize(JsonParser jsonparser,
    DeserializationContext context) throws IOException,
    JsonProcessingException {
    formatter.setTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE));
    formatter.setLenient(false);
    String date = jsonparser.getText();
    try {
      return formatter.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
