package pl.bagnolimited.template.listener;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bagnolimited.template.config.MessagesConfiguration;

public final class PlayerJoinListener extends AbstractListener {

    @Inject
    public PlayerJoinListener(BukkitAudiences bukkitAudiences, MiniMessage miniMessage, MessagesConfiguration messagesConfiguration) {
        super(bukkitAudiences, miniMessage, messagesConfiguration);
    }

    @EventHandler
    private void handle(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Audience audience = this.bukkitAudiences.player(player);

        final Component component = this.miniMessage
                .deserialize(this.messagesConfiguration.exampleListenerMessage, Placeholder.parsed("player", player.getDisplayName()));
        audience.sendMessage(component);
    }

}