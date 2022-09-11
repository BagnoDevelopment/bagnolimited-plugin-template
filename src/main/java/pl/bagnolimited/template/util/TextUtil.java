package pl.bagnolimited.template.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@UtilityClass
public final class TextUtil {

    public static String colorString(String text) {
        return text == null || text.isEmpty() ? "" : ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colorStringList(List<String> list) {
        return list.stream().map(TextUtil::colorString).collect(Collectors.toList());
    }

    public static String capitalizeFirstLetter(String text) {
        return text == null || text.isEmpty() ? "" : text.substring(0, 1).toUpperCase(Locale.ROOT) + text.substring(1);
    }

}