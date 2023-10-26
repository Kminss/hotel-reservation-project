package com.nbc.hotel.model;

public enum FindMenu {

    FIND_ALL(0),
    FIND_OWN(1),
    FIND_UUID(2),
    QUIT(3);

    private Integer number;

    FindMenu(Integer number) {
        this.number = number;
    }

    public static FindMenu toMenu(Integer number) {
        switch (number) {
            case 0 -> {
                return FIND_ALL;
            }
            case 1 -> {
                return FIND_OWN;
            }
            case 2 -> {
                return FIND_UUID;
            }
            default -> {
                return QUIT;
            }
        }
    }
}
