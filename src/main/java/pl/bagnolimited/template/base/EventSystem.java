package pl.bagnolimited.template.base;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

@RequiredArgsConstructor
public final class EventSystem {

    private final JavaPlugin plugin;

    public <T extends Event> void register(Class<T> type, Consumer<T> consumer, EventPriority priority, boolean ignoreCancelled) {
        this.plugin.getServer().getPluginManager()
                .registerEvent(
                        type,
                        new Listener() {},
                        priority,
                        (listener, event) -> consumer.accept((T) event),
                        this.plugin,
                        ignoreCancelled
                );
    }

    public <T extends Event> void register(Class<T> type, Consumer<T> consumer, EventPriority priority) {
        this.register(type, consumer, priority, false);
    }

    public <T extends Event> void register(Class<T> type, Consumer<T> consumer) {
        this.register(type, consumer, EventPriority.NORMAL);
    }

}