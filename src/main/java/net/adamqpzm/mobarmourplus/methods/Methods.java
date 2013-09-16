package net.adamqpzm.mobarmourplus.methods;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class Methods {

	private Config config;

	public Methods(Config config) {
		this.config = config;
	}

	public boolean isAllowed(EntityType mobType) {
		String mobTypeName = getMobTypeName(mobType);
		List<String> allowedMobs = this.config.getAllowedMobs();

		if (allowedMobs != null) {
			if (mobTypeName != null) {
				if (allowedMobs.contains(mobTypeName)) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean canWearArmour(EntityType mobType) {
		String mobTypeName = getMobTypeName(mobType);
		ConfigurationSection armourChance = this.config
				.getArmourChanceSection();
		if (armourChance != null && mobTypeName != null) {
			if (armourChance.contains(mobTypeName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getMobTypeName(EntityType mobType) {
		switch (mobType) {
		case BAT:
			return "bat";

		case BLAZE:
			return "blaze";

		case CAVE_SPIDER:
			return "cave_spider";

		case CHICKEN:
			return "chicken";

		case COW:
			return "cow";

		case CREEPER:
			return "creeper";

		case ENDER_DRAGON:
			return "ender_dragon";

		case ENDERMAN:
			return "enderman";

		case GHAST:
			return "ghast";

		case HORSE:
			return "horse";

		case IRON_GOLEM:
			return "iron_golem";

		case MAGMA_CUBE:
			return "magma_cube";

		case MUSHROOM_COW:
			return "mooshroom";

		case OCELOT:
			return "ocelot";

		case PIG:
			return "pig";

		case PIG_ZOMBIE:
			return "pig_zombie";

		case SHEEP:
			return "sheep";

		case SILVERFISH:
			return "silverfish";

		case SKELETON:
			return "skeleton";

		case SLIME:
			return "slime";

		case SPIDER:
			return "spider";

		case SQUID:
			return "squid";

		case VILLAGER:
			return "villager";

		case WITCH:
			return "witch";

		case WITHER:
			return "wither";

		case WOLF:
			return "wolf";

		case ZOMBIE:
			return "zombie";

		default:
			return null;
		}
	}

	public void setArmour(LivingEntity mob, EntityType mobType) {
		String mobTypeName = getMobTypeName(mobType);
		Random random = new Random();
		int helmChance = random.nextInt(100) + 1;
		int chestChance = random.nextInt(100) + 1;
		int legsChance = random.nextInt(100) + 1;
		int bootsChance = random.nextInt(100) + 1;
		int toolChance = random.nextInt(100) + 1;
		int chance = this.config.getArmourChance(mobTypeName);

		if (chance >= helmChance) {
			setHelm(mob, false);
		} else {
			setHelm(mob, true);
		}
		if (chance >= chestChance) {
			setChest(mob, false);
		} else {
			setChest(mob, true);
		}
		if (chance >= legsChance) {
			setLegs(mob, false);
		} else {
			setLegs(mob, true);
		}
		if (chance >= bootsChance) {
			setBoots(mob, false);
		} else {
			setBoots(mob, true);
		}
		if (chance >= toolChance) {
			setTool(mob, false);
		} else {
			setTool(mob, true);
		}
	}

	private void setHelm(LivingEntity mob, boolean isAir) {
		if (!isAir) {
			Random random = new Random();
			int materialNumber = random.nextInt(5);
			String helmMaterial = getMaterial(materialNumber);
			ItemStack helm = null;

			if (helmMaterial.equals("leather")) {
				helm = new ItemStack(Material.LEATHER_HELMET);
			}
			if (helmMaterial.equals("gold")) {
				helm = new ItemStack(Material.GOLD_HELMET);
			}
			if (helmMaterial.equals("chain")) {
				helm = new ItemStack(Material.CHAINMAIL_HELMET);
			}
			if (helmMaterial.equals("iron")) {
				helm = new ItemStack(Material.IRON_HELMET);
			}
			if (helmMaterial.equals("diamond")) {
				helm = new ItemStack(Material.DIAMOND_HELMET);
			}

			if (helm != null) {
				if (shouldEnchant(mob)) {
					Enchantment ench = getArmourEnchant();
					int level = getEnchantLevel(ench);
					helm.addEnchantment(ench, level);
				}
				EntityEquipment ee = mob.getEquipment();
				ee.setHelmet(helm);
			}
		} else {
			EntityEquipment ee = mob.getEquipment();
			ee.setHelmet(new ItemStack(Material.AIR));
		}
	}

	private int getEnchantLevel(Enchantment ench) {
		Random random = new Random();
		int max = ench.getMaxLevel();
		int level = random.nextInt(max) + 1;
		return level;
	}

	private Enchantment getArmourEnchant() {
		Random random = new Random();
		switch (random.nextInt(5)) {
		case 0:
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		case 1:
			return Enchantment.PROTECTION_FIRE;
		case 2:
			return Enchantment.PROTECTION_EXPLOSIONS;
		case 3:
			return Enchantment.PROTECTION_PROJECTILE;
		case 4:
			return Enchantment.THORNS;
		case 5:
			return Enchantment.DURABILITY;
		default:
			return null;
		}
	}

	private void setChest(LivingEntity mob, boolean isAir) {
		if (!isAir) {
			Random random = new Random();
			int materialNumber = random.nextInt(5);
			String chestMaterial = getMaterial(materialNumber);
			ItemStack chest = null;

			if (chestMaterial.equals("leather")) {
				chest = new ItemStack(Material.LEATHER_CHESTPLATE);
			}
			if (chestMaterial.equals("gold")) {
				chest = new ItemStack(Material.GOLD_CHESTPLATE);
			}
			if (chestMaterial.equals("chain")) {
				chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
			}
			if (chestMaterial.equals("iron")) {
				chest = new ItemStack(Material.IRON_CHESTPLATE);
			}
			if (chestMaterial.equals("diamond")) {
				chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
			}

			if (chest != null) {
				if (shouldEnchant(mob)) {
					Enchantment ench = getArmourEnchant();
					int level = getEnchantLevel(ench);
					chest.addEnchantment(ench, level);
				}
				EntityEquipment ee = mob.getEquipment();
				ee.setChestplate(chest);
			}
		} else {
			EntityEquipment ee = mob.getEquipment();
			ee.setChestplate(new ItemStack(Material.AIR));
		}
	}

	private void setLegs(LivingEntity mob, boolean isAir) {
		if (!isAir) {
			Random random = new Random();
			int materialNumber = random.nextInt(5);
			String legsMaterial = getMaterial(materialNumber);
			ItemStack legs = null;

			if (legsMaterial.equals("leather")) {
				legs = new ItemStack(Material.LEATHER_LEGGINGS);
			}
			if (legsMaterial.equals("gold")) {
				legs = new ItemStack(Material.GOLD_LEGGINGS);
			}
			if (legsMaterial.equals("chain")) {
				legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
			}
			if (legsMaterial.equals("iron")) {
				legs = new ItemStack(Material.IRON_LEGGINGS);
			}
			if (legsMaterial.equals("diamond")) {
				legs = new ItemStack(Material.DIAMOND_LEGGINGS);
			}

			if (legs != null) {
				if (shouldEnchant(mob)) {
					Enchantment ench = getArmourEnchant();
					int level = getEnchantLevel(ench);
					legs.addEnchantment(ench, level);
				}
				EntityEquipment ee = mob.getEquipment();
				ee.setLeggings(legs);
			}
		} else {
			EntityEquipment ee = mob.getEquipment();
			ee.setLeggings(new ItemStack(Material.AIR));
		}
	}

	private void setBoots(LivingEntity mob, boolean isAir) {
		if (!isAir) {
			Random random = new Random();
			int materialNumber = random.nextInt(5);
			String bootsMaterial = getMaterial(materialNumber);
			ItemStack boots = null;

			if (bootsMaterial.equals("leather")) {
				boots = new ItemStack(Material.LEATHER_BOOTS);
			}
			if (bootsMaterial.equals("gold")) {
				boots = new ItemStack(Material.GOLD_BOOTS);
			}
			if (bootsMaterial.equals("chain")) {
				boots = new ItemStack(Material.CHAINMAIL_BOOTS);
			}
			if (bootsMaterial.equals("iron")) {
				boots = new ItemStack(Material.IRON_BOOTS);
			}
			if (bootsMaterial.equals("diamond")) {
				boots = new ItemStack(Material.DIAMOND_BOOTS);
			}

			if (boots != null) {
				if (shouldEnchant(mob)) {
					Enchantment ench = getArmourEnchant();
					int level = getEnchantLevel(ench);
					boots.addEnchantment(ench, level);
				}
				EntityEquipment ee = mob.getEquipment();
				ee.setBoots(boots);
			}
		} else {
			EntityEquipment ee = mob.getEquipment();
			ee.setBoots(new ItemStack(Material.AIR));
		}
	}

	private void setTool(LivingEntity mob, boolean isAir) {
		if (!isAir) {
			Random random = new Random();
			int toolNumber = random.nextInt(21);
			ItemStack tool = getTool(toolNumber);
			if (tool != null) {
				if (shouldEnchant(mob)) {
					if (toolNumber == 0) {
						Enchantment ench = getBowEnchant();
						int level = getEnchantLevel(ench);
						tool.addEnchantment(ench, level);
					} else {
						Enchantment ench = getToolEnchant();
						int level = getEnchantLevel(ench);
						tool.addUnsafeEnchantment(ench, level);
					}
				}
				EntityEquipment ee = mob.getEquipment();
				ee.setItemInHand(tool);
			}
		} else {
			ItemStack air = new ItemStack(Material.AIR);
			EntityEquipment ee = mob.getEquipment();
			ee.setItemInHand(air);
		}
	}

	private Enchantment getToolEnchant() {
		Random random = new Random();
		switch (random.nextInt(6)) {
		case 0:
			return Enchantment.DAMAGE_ALL;
		case 1:
			return Enchantment.DAMAGE_UNDEAD;
		case 2:
			return Enchantment.DAMAGE_ARTHROPODS;
		case 3:
			return Enchantment.KNOCKBACK;
		case 4:
			return Enchantment.FIRE_ASPECT;
		case 5:
			return Enchantment.DURABILITY;
		default:
			return null;
		}
	}

	private Enchantment getBowEnchant() {
		Random random = new Random();
		switch (random.nextInt(3)) {
		case 0:
			return Enchantment.ARROW_DAMAGE;
		case 1:
			return Enchantment.ARROW_FIRE;
		case 2:
			return Enchantment.ARROW_KNOCKBACK;
		default:
			return null;
		}
	}

	private ItemStack getTool(int toolNumber) {
		switch (toolNumber) {
		case 0:
			return new ItemStack(Material.BOW);
		case 1:
			return new ItemStack(Material.WOOD_SWORD);
		case 2:
			return new ItemStack(Material.WOOD_PICKAXE);
		case 3:
			return new ItemStack(Material.WOOD_SPADE);
		case 4:
			return new ItemStack(Material.WOOD_AXE);
		case 5:
			return new ItemStack(Material.GOLD_SWORD);
		case 6:
			return new ItemStack(Material.GOLD_PICKAXE);
		case 7:
			return new ItemStack(Material.GOLD_SPADE);
		case 8:
			return new ItemStack(Material.GOLD_AXE);
		case 9:
			return new ItemStack(Material.STONE_SWORD);
		case 10:
			return new ItemStack(Material.STONE_PICKAXE);
		case 11:
			return new ItemStack(Material.STONE_SPADE);
		case 12:
			return new ItemStack(Material.STONE_AXE);
		case 13:
			return new ItemStack(Material.IRON_SWORD);
		case 14:
			return new ItemStack(Material.IRON_PICKAXE);
		case 15:
			return new ItemStack(Material.IRON_SPADE);
		case 16:
			return new ItemStack(Material.IRON_AXE);
		case 17:
			return new ItemStack(Material.DIAMOND_SWORD);
		case 18:
			return new ItemStack(Material.DIAMOND_PICKAXE);
		case 19:
			return new ItemStack(Material.DIAMOND_SPADE);
		case 20:
			return new ItemStack(Material.DIAMOND_AXE);
		default:
			return null;
		}
	}

	private boolean shouldEnchant(LivingEntity mob) {
		String mobTypeName = getMobTypeName(mob.getType());
		int chance = this.config.getArmourChance(mobTypeName);
		Random random = new Random();
		int randomNumber = random.nextInt(100) + 1;

		if (chance >= randomNumber) {
			return true;
		} else {
			return false;
		}
	}

	public void removeArmour(LivingEntity mob) {
		EntityEquipment ee = mob.getEquipment();
		ItemStack air = new ItemStack(Material.AIR);
		ee.setHelmet(air);
		ee.setChestplate(air);
		ee.setLeggings(air);
		ee.setBoots(air);
		ee.setItemInHand(air);
	}

	private String getMaterial(int materialNumber) {
		switch (materialNumber) {
		case 0:
			return "leather";
		case 1:
			return "gold";
		case 2:
			return "chain";
		case 3:
			return "iron";
		case 4:
			return "diamond";
		default:
			return null;
		}
	}
}
