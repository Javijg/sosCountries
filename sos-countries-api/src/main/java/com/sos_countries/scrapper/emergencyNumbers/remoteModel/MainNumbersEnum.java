package com.sos_countries.scrapper.emergencyNumbers.remoteModel;

public enum MainNumbersEnum {
    POLICE(0),
    AMBULANCE(1),
    FIRE(2);

    private final int numberOrder;

    MainNumbersEnum(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getNumberOrder() {
        return numberOrder;
    }
}