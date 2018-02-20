package napodev.app.test.utils;

import java.text.DecimalFormat;

/**
 * Created by opannapo on 2/20/18.
 */

public class DecimalHelper {
    public static String format(double val) {
        return new DecimalFormat("#.00").format(val);
    }
}
