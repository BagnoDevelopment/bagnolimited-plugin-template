package pl.bagnolimited.template.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class ItemBuilder {

    private final Material material;

    private final ItemMeta itemMeta;
    private int amount;
    private short durability;

    public ItemBuilder(Material material) {
        if (material != Material.AIR) {
            this.material = material;
            this.itemMeta = new ItemStack(material).getItemMeta();
            this.amount = 1;
            this.durability = material.getMaxDurability();
            return;
        }
        throw new IllegalArgumentException("Material can't be air!");
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        final List<String> toSet = Arrays.asList(lore);
        this.itemMeta.setLore(toSet);
        return this;
    }

    public ItemBuilder addLore(String line) {
        final List<String> currentLore = this.itemMeta.getLore();
        currentLore.add(line);
        this.itemMeta.setLore(currentLore);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        this.itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        final Set<ItemFlag> toSet = itemMeta.getItemFlags();
        toSet.add(itemFlag);
        this.itemMeta.addItemFlags(toSet.toArray(new ItemFlag[0]));
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        this.durability = (short) durability;
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        final ItemStack toReturn = new ItemStack(material);
        toReturn.setDurability(durability);
        toReturn.setAmount(this.amount);
        toReturn.setItemMeta(this.itemMeta);
        return toReturn;
    }

}