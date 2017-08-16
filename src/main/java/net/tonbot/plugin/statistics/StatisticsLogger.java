package net.tonbot.plugin.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tonbot.common.PeriodicTask;
import sx.blah.discord.api.IDiscordClient;

class StatisticsLogger extends PeriodicTask {

	private static final Logger LOG = LoggerFactory.getLogger(StatisticsLogger.class);

	public StatisticsLogger(IDiscordClient discordClient, long periodMs) {
		super(discordClient, periodMs);
	}

	@Override
	public void performTask() {
		IDiscordClient discordClient = this.getDiscordClient();
		LOG.info("Currently in " + discordClient.getGuilds().size() + " guilds.");
		LOG.info("Is ready? " + discordClient.isReady());
		LOG.info("Is logged in? " + discordClient.isLoggedIn());
	}
}
