package net.tonbot.plugin.statistics;

import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import net.tonbot.common.PeriodicTask;
import net.tonbot.common.TonbotPlugin;
import net.tonbot.common.TonbotPluginArgs;

public class StatisticsPlugin extends TonbotPlugin {

	private Injector injector;

	public StatisticsPlugin(TonbotPluginArgs args) {
		super(args);

		this.injector = Guice.createInjector(new StatisticsModule(args.getPrefix(), args.getDiscordClient()));
	}

	@Override
	public String getFriendlyName() {
		return "Statistics Logger";
	}

	@Override
	public String getActionDescription() {
		return "Displays statistics for this bot";
	}

	@Override
	public boolean isHidden() {
		return true;
	}

	@Override
	public Set<PeriodicTask> getPeriodicTasks() {
		return injector.getInstance(Key.get(new TypeLiteral<Set<PeriodicTask>>() {
		}));
	}
}
