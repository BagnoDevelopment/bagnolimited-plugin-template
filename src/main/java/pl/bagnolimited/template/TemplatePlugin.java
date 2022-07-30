package pl.bagnolimited.template;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bagnolimited.template.base.EventSystem;

import java.io.File;
import java.io.IOException;

import static pl.bagnolimited.template.util.TextUtil.*;

public final class TemplatePlugin extends JavaPlugin {

    @Getter
    private YamlDocument mainConfiguration,
            messagesConfiguration;

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

        registerConfigurationFiles();
        this.eventSystem = new EventSystem(this);

        this.eventSystem.register(PlayerJoinEvent.class, event ->
                event.getPlayer().sendMessage(colorString(messagesConfiguration.getString("example"))));

        final long milliseconds = Math.abs(ENABLE_TIME - System.currentTimeMillis());
        getLogger().info("Plugin enabled in " + milliseconds + "ms.");
    }

    /*
     * Plugin shutdown logic.
     * Save user data, disconnect from database etc.
     */
    @Override
    public void onDisable() {}

    /*
     * Here you can register all configuration files.
     * Just use an example.
     */
    @SneakyThrows(IOException.class)
    private void registerConfigurationFiles() {
        this.mainConfiguration = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                GeneralSettings.DEFAULT,
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.DEFAULT,
                UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        this.messagesConfiguration = YamlDocument.create(new File(getDataFolder(), "messages.yml"), getResource("messages.yml"));
    }

}