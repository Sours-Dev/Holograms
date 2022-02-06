package sours.holograms.holo;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

import sours.holograms.sHolograms;
import sours.holograms.untils.c;

public class holoEVNT {

	private configData configData = new configData();
	private sHolograms plugin = sHolograms.getInstance();
	
	public void loadHolograms() {
		configData.setupData();
		Location loc;

		
		   for(String holograms : configData.getData().getKeys(false)) {
              loc = new Location(Bukkit.getWorld(configData.getData().getString(holograms + ".Location.World")),
            		  configData.getData().getDouble(holograms + ".Location.X"),
            		  	configData.getData().getDouble(holograms + ".Location.Y"),
            		  		configData.getData().getDouble(holograms + ".Location.Z"));

              Hologram hologram = HologramsAPI.createHologram(plugin, loc);	
              for(String linesS : configData.getData().getConfigurationSection(holograms + ".Lines").getKeys(false)) {
            	TextLine line =   hologram.insertTextLine(Integer.valueOf(linesS) - 1, c.f(configData.getData().getString(holograms + ".Lines." + linesS)));
              }
              plugin.getHologramsList().put(holograms, hologram);
           }
		
	
	}
	

	public void createHologram(String id, Location loc , String text) {
	
	
		
		
		Vector direction = loc.getDirection();
		direction.multiply(2);
		loc.add(direction).add(0, 2, 0);	
		
		//Save hologram to datebase
		// location / lines 
		configData.setupData();
		
		configData.getData().set(id + ".Location.World", loc.getWorld().getName());
		configData.getData().set(id + ".Location.X", loc.getX());
		configData.getData().set(id + ".Location.Y", loc.getY());
		configData.getData().set(id + ".Location.Z", loc.getZ());
		configData.getData().set(id + ".Lines.1", text);
		configData.saveData();
		
		
		Hologram hologram = HologramsAPI.createHologram(plugin, loc);		
		TextLine line1 = hologram.insertTextLine(0, c.f(text));
		

		
		plugin.getHologramsList().put(id, hologram);
	}
	
	public void deleteHologram(String id) {
		
		configData.setupData();
		configData.getData().set(id, null);
		configData.saveData();
		
		Hologram hologram = plugin.getHologramsList().get(id);
		hologram.delete();
		plugin.getHologramsList().remove(id);
	}
	
	public void addLine(String name, String text) {
		
	}
	
	public void removeLine(String name, int line) {
		
	}
	
	public void editLine(String name, int line) {
		
	}
	
	public void insertLine(String name, int line) {
		//after the selected line
	}
	
	public boolean sameName(String id) {
		configData.setupData();
		if(configData.getData().contains(id) || plugin.getHologramsList().containsKey(id)) {
			return true;
		}
			
		return false;
	}
	public void test1(Player p) {
		
		for(Hologram holo : plugin.getHologramsList().values()) {
			VisibilityManager visibilityManager = holo.getVisibilityManager();
			visibilityManager.showTo(p);
		}
	}
	public void test(Player p) {
		
		for(Hologram holo : plugin.getHologramsList().values()) {
			VisibilityManager visibilityManager = holo.getVisibilityManager();
			visibilityManager.hideTo(p);
		}
	}
}
