package com.nbc.hotel.model;

import com.nbc.hotel.exception.ReservationNotFoundException;

import java.util.Scanner;
import java.util.UUID;

public class InputManager {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * UUID 문자열 입력받는 메서드
     *
     * @return
     */
    public static UUID inputUUID() {
        try {
            String input = sc.nextLine();
            return UUID.fromString(input);
        } catch (IllegalArgumentException exception) {
            throw new ReservationNotFoundException("잘못된 UUID 입력입니다.");
        }
    }

    public static Integer inputMenuNumber(int menuSize) {
        int result = -1;
        while (result == -1) {
            try {
                result = Integer.parseInt(sc.nextLine());
                if (!isValidMenuNumber(result, menuSize))
                    result = -1;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("잘못된 입력입니다.");
            }
        }
        return result;
    }

    private static boolean isValidMenuNumber(int number, int menuSize) {
        if (number > 0 && number < menuSize) {
            return true;
        }
        return false;
    }

    public static String handleInput() {
        return sc.nextLine();
    }
}
