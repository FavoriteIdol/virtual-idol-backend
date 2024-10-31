package com.outsider.virtual.concert.command.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FeverVFX {
    VFX1(1),
    VFX2(2),
    VFX3(3);

    private final int value;

    FeverVFX(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static FeverVFX fromValue(int value) {
        for (FeverVFX vfx : FeverVFX.values()) {
            if (vfx.value == value) {
                return vfx;
            }
        }
        throw new IllegalArgumentException("Invalid FeverVFX value: " + value);
    }
}
