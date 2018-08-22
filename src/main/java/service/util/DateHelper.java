package service.util;

import java.sql.Timestamp;

public class DateHelper {

    private static final int MILLISECONDS_IN_A_DAY = 86_400_400;
    private static final String SECONDS = ":00";

    public static int getCeilDaysOfDateDifference(Timestamp date1, Timestamp date2){
        double milliSecondsDifference = Math.abs(date1.getTime()-date2.getTime());
        double days = milliSecondsDifference/MILLISECONDS_IN_A_DAY;
        return (int)Math.ceil(days);
    }

    public static int getCeilDaysOfDateDifference(String date1, String date2){
        Timestamp timestampDate1 = Timestamp.valueOf(date1 + SECONDS);
        Timestamp timestampDate2 = Timestamp.valueOf(date2 + SECONDS);
        double milliSecondsDifference = Math.abs(timestampDate1.getTime()-timestampDate2.getTime());
        double days = milliSecondsDifference/MILLISECONDS_IN_A_DAY;
        return (int)Math.ceil(days);
    }

}
