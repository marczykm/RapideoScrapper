package pl.marczyk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mm on 18.10.2016.
 */
public class DateParser {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-YYYY hh:mm"); // 19-10-2016 11:05;

    public static Date parse(String dateString) throws ParseException {
        return SDF.parse(dateString);
    }
}
