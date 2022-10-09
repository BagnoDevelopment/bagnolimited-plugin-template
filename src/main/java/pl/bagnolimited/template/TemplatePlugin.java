package pl.bagnolimited.template;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bagnolimited.template.config.ConfigurationFactory;
import pl.bagnolimited.template.config.MessagesConfiguration;
import pl.bagnolimited.template.listener.PlayerJoinListener;

import java.io.File;

public final class TemplatePlugin extends JavaPlugin {

    private final File MESSAGES_FILE = new File(this.getDataFolder(), "messages.yml");

    @Override /* Plugin startup logic */
    public void onEnable() {
        final long ENABLE_TIME = System.currentTimeMillis();

        final Injector injector = OkaeriInjector.create();

        final ConfigurationFactory configurationFactory = new ConfigurationFactory();

        final BukkitAudiences bukkitAudiences = BukkitAudiences.create(this);
        injector.registerInjectable(bukkitAudiences);

        final MiniMessage miniMessage = MiniMessage.miniMessage();
        injector.registerInjectable(miniMessage);

        final MessagesConfiguration messagesConfiguration = configurationFactory
                .createDefault(MessagesConfiguration.class, this.MESSAGES_FILE);
        injector.registerInjectable(messagesConfiguration);

        /* Register listeners */
        this.getServer().getPluginManager().registerEvents(injector.createInstance(PlayerJoinListener.class), this);

        final long milliseconds = Math.abs(ENABLE_TIME - System.currentTimeMillis());
        this.getLogger().info(" _                            _  _         _  _            _ ");
        this.getLogger().info("| |__  __ _  __ _  _ _   ___ | |(_) _ __  (_)| |_  ___  __| |");
        this.getLogger().info("| '_ \\/ _` |/ _` || ' \\ / _ \\| || || '  \\ | ||  _|/ -_)/ _` |");
        this.getLogger().info("|_.__/\\__,_|\\__, ||_||_|\\___/|_||_||_|_|_||_| \\__|\\___|\\__,_|");
        this.getLogger().info("            |___/                                            ");

        this.getLogger().info(String.format("Plugin enabled in %d ms.", milliseconds));
    }

    @Override /* Plugin shutdown logic */
    public void onDisable() {}

}