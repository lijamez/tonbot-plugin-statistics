package net.tonbot.plugin.statistics;

import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.tonberry.tonbot.common.PeriodicTask;
import com.tonberry.tonbot.common.Prefix;

import sx.blah.discord.api.IDiscordClient;

class StatisticsModule extends AbstractModule {

	private static final long PERIOD_MS = 300000;

	private final String prefix;
	private final IDiscordClient discordClient;

	public StatisticsModule(String prefix, IDiscordClient discordClient) {
		this.prefix = Preconditions.checkNotNull(prefix, "prefix must be non-null");
		this.discordClient = Preconditions.checkNotNull(discordClient, "discordClient must be non-null");
	}

	public void configure() {
		bind(String.class).annotatedWith(Prefix.class).toInstance(prefix);
		bind(IDiscordClient.class).toInstance(discordClient);
	}

	@Provides
	@Singleton
	Set<PeriodicTask> periodicTasks() {
		StatisticsLogger diagnosticsLogger = new StatisticsLogger(discordClient, PERIOD_MS);

		return ImmutableSet.of(diagnosticsLogger);
	}
}
