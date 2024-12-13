package com.workout.tracker.utility;

public class Utility {
    public static boolean isStringNonEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }

    public static boolean isStringEmptyOrNull(String string) {
        return string == null || string.trim().isEmpty();
    }
}
