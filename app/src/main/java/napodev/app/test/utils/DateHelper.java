package napodev.app.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by opannapo on 2/20/18.
 */

public class DateHelper {
    public static String formatToMyZone(String val) {
        String result;
        try {
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy");
            Date date = dateParser.parse(val);
            result = dateFormatter.format(date);
        } catch (Exception e) {
            result = "-";
        }

        return result;
    }
}
