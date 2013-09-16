package net.adamqpzm.mobarmourplus;

import net.adamqpzm.mobarmourplus.commands.ReloadCommand;
import net.adamqpzm.mobarmourplus.events.MobSpawnEvent;
import net.adamqpzm.mobarmourplus.methods.Config;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MobArmourPlus extends JavaPlugin {

	public void onEnable() {
		Config config = new Config(this);
		config.createDefaultConfig();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new MobSpawnEvent(config), this);

		getCommand("mobarmourplus").setExecutor(new ReloadCommand(config));
	}
}
