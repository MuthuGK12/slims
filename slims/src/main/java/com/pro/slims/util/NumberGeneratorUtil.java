package com.pro.slims.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NumberGeneratorUtil {

    private NumberGeneratorUtil() {}

    public static String generateSampleNumber(Long sequence) {

        String yearMonth =
                LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMM"));

        return String.format(
                "SMP-%s-%04d",
                yearMonth,
                sequence
        );
    }

    public static String generateJobNumber(Long sequence) {

        String yearMonth =
                LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMM"));

        return String.format(
                "JOB-%s-%04d",
                yearMonth,
                sequence
        );
    }
}