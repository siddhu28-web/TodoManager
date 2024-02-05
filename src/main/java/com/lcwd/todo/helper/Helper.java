package com.lcwd.todo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class Helper {

    public static Date parseDate(LocalDateTime dateStr) throws ParseException{
        //To convert LocalDateTime to Date Object,we first convert LocalDateTime into Instant
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        //Date parse = simpleDateFormat.parse(dateStr);
        //return parse;
        System.out.println(dateStr);
        //Instant instant = dateStr.toInstant(ZoneOffset.UTC);
        Instant instant = dateStr.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
