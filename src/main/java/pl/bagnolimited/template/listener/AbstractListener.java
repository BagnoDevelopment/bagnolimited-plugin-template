package pl.bagnolimited.template.listener;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.Listener;
import pl.bagnolimited.template.config.MessagesConfiguration;

@RequiredArgsConstructor
public abstract class AbstractListener implements Listener {
    protected final BukkitAudiences bukkitAudiences;
    protected final MiniMessage miniMessage;
    protected final MessagesConfiguration messagesConfiguration;
}