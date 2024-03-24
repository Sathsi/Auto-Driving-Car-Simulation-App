package com.gic.model;

import java.util.Arrays;

public enum Direction {
    N, E, S, W;

    public static String[] toStringArray() {
        return Arrays.stream(values())
                .map(Enum::name)
                .toArray(String[]::new);
    }
}
