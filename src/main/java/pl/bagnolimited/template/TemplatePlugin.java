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
//import pl.bagnolimited.template.database.impl.MongoImpl;

import java.io.File;
import java.io.IOException;

import static pl.bagnolimited.template.util.TextUtil.*;

public final class TemplatePlugin extends JavaPlugin {

    private long ENABLE_TIME;

    @Getter
    private YamlDocument mainConfiguration,
                         messagesConfiguration;

    @Getter
    private EventSystem eventSystem;

    //@Getter
    //private MongoImpl mongo;

    @Override
    public void onLoad() {
        this.ENABLE_TIME = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Remember to register config files before connecting to database!
        registerConfigurationFiles();
        //this.mongo = new MongoImpl(this);
        //this.setupDatabase();

        // This allows you to dynamically register events
        this.eventSystem = new EventSystem(this);
        eventSystem.register(PlayerJoinEvent.class, event
                -> event.getPlayer().sendMessage(colorString(messagesConfiguration.getString("server.player-join"))));

        // Print plugin enable time
        final long milliseconds = Math.abs(ENABLE_TIME - System.currentTimeMillis());
        getLogger().info("Plugin enabled in " + milliseconds + "ms.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //this.mongo.disconnect();
    }

    @SneakyThrows(IOException.class)
    private void registerConfigurationFiles() {
        this.mainConfiguration = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                GeneralSettings.DEFAULT,
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.DEFAULT,
                UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        this.messagesConfiguration = YamlDocument.create(new File(getDataFolder(), "messages.yml"), getResource("messages.yml"));
    }

    /*
     *
     * private void setupDatabase() {
     *   if (mongo.connect()) return;
     *   if (mainConfiguration.getBoolean("should-disable-server-if-failed-to-connect")) getServer().shutdown();
     * }
     *
     */

}