package pl.bagnolimited.template.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ProgressUtil {

    public static String getPercentage(int currentNumber, int completedNumber) {
        final int percentage = currentNumber * 100 / completedNumber;
        return String.valueOf(Math.round(percentage * 10) / 10);
    }

}