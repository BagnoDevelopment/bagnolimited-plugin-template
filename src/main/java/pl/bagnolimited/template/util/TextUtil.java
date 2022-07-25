package pl.bagnolimited.template.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UtilityClass
public final class TextUtil {

    public static String colorString(String text) {
        return text == null || text.isEmpty() ? "" : ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colorStringList(List<String> list) {
        final List<String> toReturn = new ArrayList<>();
        list.forEach(text -> toReturn.add(colorString(text)));
        return toReturn;
    }

    public static String capitalizeFirstLetter(String text) {
        return text == null || text.isEmpty() ? "" : text.substring(0, 1).toUpperCase(Locale.ROOT) + text.substring(1);
    }

}