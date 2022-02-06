package sours.holograms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;

import sours.holograms.sHolograms;

public class playerEVNT implements Listener{

	private sHolograms plugin = sHolograms.getInstance();
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		for(Hologram holo : plugin.getHologramsList().values()) {
			VisibilityManager visibilityManager = holo.getVisibilityManager();
			visibilityManager.showTo(p);
		}
	}
	
}
