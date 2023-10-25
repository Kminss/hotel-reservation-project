package com.nbc.hotel.model;

public enum FindMenu {

    FIND_ALL(1),
    FIND_OWN(2),
    FIND_UUID(3),
    QUIT(4);

    private Integer number;

    FindMenu(Integer number) {
        this.number = number;
    }

    public static FindMenu toMenu(Integer number) {
        switch (number) {
            case 1 -> {
                return FIND_ALL;
            }
            case 2 -> {
                return FIND_OWN;
            }
            case 3 -> {
                return FIND_UUID;
            }
            default -> {
                return QUIT;
            }
        }
    }
}
