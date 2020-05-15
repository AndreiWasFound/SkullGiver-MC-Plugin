package me.AndreiWasFound.Skull;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("skull")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You cannot use this command, console!");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				// /skull <-- give the player running this command their head
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&6You have been given the skull of &c" + player.getName()));
				player.getInventory().addItem(getPlayerHead(player.getName())); 
				return true;
			}
			// /skull <player> <-- give the player running this command <player> head
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&6You have been given the skull of &c" + args[0]));
			player.getInventory().addItem(getPlayerHead(args[0])); 
			return true;
		}
		
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getPlayerHead(String player) {
		
		boolean isNewVersion = Arrays.stream(Material.values())
				.map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
		
		
		
		Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
		
		ItemStack item = new ItemStack(type, 1);
		
		if (!isNewVersion)
			item.setDurability((short) 3);
		
		
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(player);
		
		item.setItemMeta(meta);
		
		
		
		return item;
	}

}
















