package net.tonbot.plugin.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import net.tonbot.common.PeriodicTask;
import sx.blah.discord.api.IDiscordClient;

class StatisticsLogger extends PeriodicTask {

	private static final Logger LOG = LoggerFactory.getLogger(StatisticsLogger.class);

	private final IDiscordClient discordClient;
	
	public StatisticsLogger(IDiscordClient discordClient, long periodMs) {
		super(periodMs);
		
		this.discordClient = Preconditions.checkNotNull(discordClient, "discordClient must be non-null.");
	}

	@Override
	public void performTask() {
		LOG.info("Currently in " + discordClient.getGuilds().size() + " guilds.");
		LOG.info("Is ready? " + discordClient.isReady());
		LOG.info("Is logged in? " + discordClient.isLoggedIn());
	}
}
