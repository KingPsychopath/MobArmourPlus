package net.adamqpzm.mobarmourplus.methods;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class Config {

	private Plugin plugin;

	public Config(Plugin plugin) {
		this.plugin = plugin;
	}

	public void createDefaultConfig() {
		this.plugin.saveDefaultConfig();
	}
	
	public void reload() {
		this.plugin.reloadConfig();
	}

	public List<String> getAllowedMobs() {
		@SuppressWarnings("unchecked")
		List<String> allowedMobs = (List<String>) this.plugin.getConfig()
				.getList("allowed-mob-spawns");
		return allowedMobs;
	}

	public ConfigurationSection getArmourChanceSection() {
		ConfigurationSection cs = this.plugin.getConfig()
				.getConfigurationSection("armour-chance");
		return cs;
	}

	public int getArmourChance(String mobTypeName) {
		int chance = this.plugin.getConfig().getInt(
				"armour-chance." + mobTypeName);
		return chance;
	}
}
