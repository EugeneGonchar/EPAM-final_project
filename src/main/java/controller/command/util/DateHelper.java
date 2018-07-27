package controller.command.util;

import java.sql.Timestamp;

public class DateHelper {

    private static final int MILLISECONDS_IN_A_DAY = 86_400_400;

    public static int getCeilDaysOfDateDifference(Timestamp date1, Timestamp date2){
        double milliSecondsDifference = Math.abs(date1.getTime()-date2.getTime());
        double days = milliSecondsDifference/MILLISECONDS_IN_A_DAY;
        return (int)Math.ceil(days);
    }

}
