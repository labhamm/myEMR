package com.hackathon.vault.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public Date getCurrentDate() {
		DateTime now = new DateTime(); // Default time zone.
		DateTime dateTime = now.toDateTime(DateTimeZone.UTC);
		return dateTime.toDate();
	}

}
