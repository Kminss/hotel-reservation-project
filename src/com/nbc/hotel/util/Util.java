package com.nbc.hotel.util;

import com.nbc.hotel.exception.ReservationNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Util {
    public static UUID converStringToUuid(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException exception) {
            throw new ReservationNotFoundException();
        }
    }

    public static boolean isValidDate(String selectDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 날짜 검사 결과를 저장할 변수입니다.
        dateFormat.setLenient(false);
        // yyyy-MM-dd형식에 맞는지
        try {
            Date parsedDate = dateFormat.parse(selectDate);
            // 입력된 날짜를 LocalDate로 변환
            LocalDate selectedDate = LocalDate.parse(selectDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // 현재 날짜와 비교
            LocalDate currentDate = LocalDate.now();
            // 입력된 날짜가 현재 날짜 이전이면 유효하지 않음
            if (selectedDate.isBefore(currentDate)) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
