package com.outsider.virtual.concert.command.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppearedVFX {
    VFX1(1),
    VFX2(2),
    VFX3(3);

    private final int value;

    AppearedVFX(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static AppearedVFX fromValue(int value) {
        for (AppearedVFX vfx : AppearedVFX.values()) {
            if (vfx.value == value) {
                return vfx;
            }
        }
        throw new IllegalArgumentException("Invalid AppearedVFX value: " + value);
    }
}