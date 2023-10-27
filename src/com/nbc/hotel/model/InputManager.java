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
                validateMenuNumber(result, menuSize);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                result = -1;
            }
        }
        return result - 1; // 인덱스라서 -1
    }

    private static void validateMenuNumber(int number, int menuSize) throws Exception {
        if (!(number > 0 && number <= menuSize)) {
            throw new Exception("잘못된 메뉴 입력입니다. 다시 입력해주세요.");
        }
    }

    public static String handleInput() {
        return sc.nextLine();
    }
}
