package com.bskyb.internettv.parental_control_service;

import java.util.HashMap;
import java.util.Map;

import com.bskyb.internettv.thirdparty.TechnicalFailureException;

public enum ParentalControlEnum {
    U("U",1), PG("PG",2), R12("12",3), R15("15",4), R18("18",5);

    private static final Map<String, ParentalControlEnum> BY_LABEL = new HashMap<String, ParentalControlEnum>();
    private static final Map<Integer, ParentalControlEnum> BY_VALUE = new HashMap<Integer, ParentalControlEnum>();
    static {
        for (ParentalControlEnum e : values()) {
            BY_LABEL.put(e.label, e);
            BY_VALUE.put(e.value, e);
        }
    }

    public final String label;
    public final int value;

    private ParentalControlEnum(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public static ParentalControlEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static ParentalControlEnum valueOfValue(int number) {
        return BY_VALUE.get(number);
    }

    public static Boolean allowAccess(ParentalControlEnum movie, ParentalControlEnum watcher) throws TechnicalFailureException {
        if (movie == null || watcher == null) throw new TechnicalFailureException();
        return movie.value <= watcher.value; 
    }
}

