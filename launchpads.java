//Credit to codename_B for source files. Edited and updated by codename_C ;) .


package com.mooo.GuildHub;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public final class LaunchPads extends JavaPlugin implements Listener{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static LaunchPads plugin;
	
	private ArrayList<String> jumpers = new ArrayList <String>();
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("LaunchPads has been Enabled! Keep on jumping!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("LaunchPads has been Disabled! Oh nuuuu!!!");
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.STONE_PLATE){
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
			e.getPlayer().setVelocity(new Vector(e.getPlayer().getLocation().getX(),1.0D, e.getPlayer().getLocation().getZ()));
			jumpers.add(e.getPlayer().toString());
			
		}
	}//public void onPlayerMove

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(e.getCause() == EntityDamageEvent.DamageCause.FALL && jumpers.contains(p)) {
				e.setDamage(0.0);;
				jumpers.remove(p);
			}//e.getCause()
		}//e.getEntity
		
	}//EntityDamageEvent
	
	
}//ends class
