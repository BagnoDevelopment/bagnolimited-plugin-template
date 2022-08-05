package pl.bagnolimited.template;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import lombok.Getter;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bagnolimited.template.base.EventSystem;
import pl.bagnolimited.template.config.MessageConfiguration;

import java.io.File;

import static pl.bagnolimited.template.util.TextUtil.*;

public final class TemplatePlugin extends JavaPlugin {

    @Getter
    private MessageConfiguration messageConfiguration;

    @Getter
    private EventSystem eventSystem;

    /*
     * Plugin startup logic.
     * Connect to database, load user data etc.
     * Remember to load configuration files before connecting to database!
     */
    @Override
    public void onEnable() {
        long ENABLE_TIME = System.currentTimeMillis();
        this.prepareConfiguration();
        this.eventSystem = new EventSystem(this);

        this.eventSystem.register(PlayerJoinEvent.class, event ->
                event.getPlayer().sendMessage(colorString(messageConfiguration.example)));

        final long milliseconds = Math.abs(ENABLE_TIME - System.currentTimeMillis());
        getLogger().info("Plugin enabled in " + milliseconds + "ms.");
    }

    /*
     * Plugin shutdown logic.
     * Save user data, disconnect from database etc.
     */
    @Override
    public void onDisable() {}

    private void prepareConfiguration() {
        this.messageConfiguration = ConfigManager.create(MessageConfiguration.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(getDataFolder(), "messages.yml"));
            it.saveDefaults();
            it.load(true);
        });
    }

}