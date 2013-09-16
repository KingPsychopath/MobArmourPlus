package net.adamqpzm.mobarmourplus.commands;

import net.adamqpzm.mobarmourplus.methods.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

	private Config config;

	public ReloadCommand(Config config) {
		this.config = config;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mobarmourplus")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("reload")) {
					this.config.reload();
					sender.sendMessage(ChatColor.GREEN
							+ "[MAP] config.yml reloaded!");
					return true;
				} else {
					sender.sendMessage(ChatColor.RED
							+ "[MAP] Syntax error. Usage: /map reload");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "[MAP] /map reload"
						+ " - Reloads config.yml");
				return true;
			}
		}

		return false;
	}

}
