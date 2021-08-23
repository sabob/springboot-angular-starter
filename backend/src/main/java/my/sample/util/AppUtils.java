package my.sample.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AppUtils {

    public static String generateReference() {
         int random = new Random().nextInt(1000);
         return "" + random;
    }
}
