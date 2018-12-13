package io.github.jhipster.application.web.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueKeyGenerator {


static String getuniquekey() {
	Date date = new Date();
    String strDateFormat = "ddmmhhmmss";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate= dateFormat.format(date);
	return formattedDate ;
}
}
