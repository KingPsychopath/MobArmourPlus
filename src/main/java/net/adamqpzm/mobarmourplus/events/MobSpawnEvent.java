package net.adamqpzm.mobarmourplus.events;

import net.adamqpzm.mobarmourplus.methods.Config;
import net.adamqpzm.mobarmourplus.methods.Methods;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobSpawnEvent implements Listener {

	private Methods methods;

	public MobSpawnEvent(Config config) {
		this.methods = new Methods(config);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMobSpawn(CreatureSpawnEvent event) {

		if (event.getSpawnReason() == SpawnReason.NATURAL
				|| event.getSpawnReason() == SpawnReason.VILLAGE_INVASION
				|| event.getSpawnReason() == SpawnReason.REINFORCEMENTS) {
			EntityType mobType = event.getEntityType();
			if (this.methods.isAllowed(mobType)) {
				if (this.methods.canWearArmour(mobType)) {
					this.methods.setArmour(event.getEntity(), mobType);
				} else {
					this.methods.removeArmour(event.getEntity());
				}
			} else {
				event.setCancelled(true);
			}
		}
	}
}
