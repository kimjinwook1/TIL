package org.zerock.w3.util;


import java.time.LocalDate;

public class StringUtil {

    public static Long parseLong(String str, Long defaultValue) {

        Long value = null;
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public static LocalDate parseLocalDate(String str) {

        LocalDate date = null;

        try {
            date = LocalDate.parse(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static boolean parseBoolean(String str) {

        boolean value = false;

        try {
            value = Boolean.parseBoolean(str);
            value = str.equals("on") ? true : Boolean.parseBoolean(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return value;
    }

}
