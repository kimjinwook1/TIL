package org.zerock.w3.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getStr(LocalDate localDate) {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }
}
