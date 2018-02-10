package net.tonbot.plugin.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;
import sx.blah.discord.handle.obj.IGuild;

class GuildListener {

	private static final Logger LOG = LoggerFactory.getLogger(GuildListener.class);

	private final IDiscordClient discordClient;

	@Inject
	public GuildListener(IDiscordClient discordClient) {
		this.discordClient = Preconditions.checkNotNull(discordClient, "discordClient must be non-null.");
	}

	@EventSubscriber
	public void onGuildLeave(GuildLeaveEvent event) {
		IGuild guild = event.getGuild();
		LOG.info("Tonbot has left guild '{}' (ID {})", guild.getName(), guild.getLongID());
		logGuildCount();
	}

	@EventSubscriber
	public void onGuildCreate(GuildCreateEvent event) {
		IGuild guild = event.getGuild();
		LOG.info("Tonbot has connected to guild '{}' (ID {})", guild.getName(), guild.getLongID());
		logGuildCount();
	}

	private void logGuildCount() {
		LOG.info("Currently connected to {} guilds.", discordClient.getGuilds().size());
	}
}
