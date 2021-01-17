package com.hrms.testcases;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
     */

    public static String findDay(int month, int day, int year) {

        LocalDate dt = LocalDate.of(year,month,day);
        String fin = String.valueOf(dt.getDayOfWeek());
        return fin;
    }

}

public class testik {
    public static void main(String[] args) throws IOException {



        String res = Result.findDay(8,05,2015);
        System.out.println(res);
    }
}
