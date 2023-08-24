package com.lgq;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.DatePrinter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeRangeFetcher {
    public static void main1(String[] args) {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 0, 0, 0); // Set the start date and time
        LocalDateTime endDateTime = LocalDateTime.of(2022, 3, 31, 23, 59, 59); // Set the end date and time

        fetchTimeRange(startDateTime, endDateTime);
    }

    public static void fetchTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)||startDateTime.equals(endDateTime)) {
            System.out.println(startDateTime);
            return;
        }

        // Get data logic
        LocalDateTime nextStartDateTime = startDateTime.plusDays(7); // Increment by 7 days
        if (nextStartDateTime.isAfter(endDateTime)) {
            nextStartDateTime = endDateTime; // If next start date exceeds end date, set it to the end date
        }

        if (nextStartDateTime.getMonthValue() > startDateTime.getMonthValue()) {
            System.out.println("+++++++++++"+nextStartDateTime.getMonthValue());
            nextStartDateTime = startDateTime.withDayOfMonth(1).plusMonths(1); // Move to the first day of the next month
        }

        System.out.println("Start Time: " + startDateTime + ", End Time: " + nextStartDateTime.minusSeconds(1));

        fetchTimeRange(nextStartDateTime, endDateTime); // Recursive call with the next start date and end date
    }
    public static void main(String[] args) {
        //DateTime parse = DateUtil.parse("20230314");
        //DateTime parse1 =DateUtil.parse("20230817");
        double exchangeRate = 0.1; // Exchange rate from USD to INR
        System.out.println((int) exchangeRate);
        // fetchTimeRange(parse, parse1);
    }
    public static void fetchTimeRange(DateTime startDateTime, DateTime endDateTime) {
        if(startDateTime.after(endDateTime)||startDateTime.equals(endDateTime)){
            return;
        }
        DateTime nextStartDateTime = DateUtil.offsetDay(startDateTime, 10);

        // Get data logic
        if (nextStartDateTime.isAfter(endDateTime)) {
            nextStartDateTime = endDateTime; // If next start date exceeds end date, set it to the end date
        }


        System.out.println("Start Time: " + DateUtil.format(startDateTime, DatePattern.PURE_DATE_PATTERN) + ", End Time: " + DateUtil.format(nextStartDateTime, DatePattern.PURE_DATE_PATTERN));

        fetchTimeRange(nextStartDateTime, endDateTime); // Recursive call with the next start date and end date
    }
}