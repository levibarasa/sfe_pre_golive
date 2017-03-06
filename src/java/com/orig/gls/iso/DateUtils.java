package com.orig.gls.iso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    Date currDate;
    SimpleDateFormat sdf;
    SimpleDateFormat lda;
    SimpleDateFormat cyear;
    SimpleDateFormat cdate;
    SimpleDateFormat cMonth;
    SimpleDateFormat dbFormat;
    SimpleDateFormat log_dt;

    public DateUtils() {
        currDate = new Date();
        sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        dbFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        lda = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        cyear = new SimpleDateFormat("yyyy", Locale.getDefault());
        cdate = new SimpleDateFormat("dd", Locale.getDefault());
        cMonth = new SimpleDateFormat("MM", Locale.getDefault());
        log_dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
    }

    public String getCurrentDate() {
        String d = sdf.format(currDate);
        return d;
    }

    public String getLogoutDate() {
        String d = log_dt.format(currDate);
        return d;
    }

    public String getCurrentDateAndTime() {
        String d = lda.format(currDate);
        return d;
    }

    public String getCurrentYear() {
        String d = cyear.format(currDate);
        return d;
    }

    public String getCurrentDay() {
        String d = cdate.format(currDate);
        return d;
    }

    public String getCurrentMonth() {
        String d = cMonth.format(currDate);
        return d;
    }
}
