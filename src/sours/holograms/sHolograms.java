package sours.holograms;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import sours.holograms.commands.hologramsCMD;
import sours.holograms.commands.testCMD;
import sours.holograms.events.playerEVNT;
import sours.holograms.untils.c;
import sours.holograms.untils.configLang;
import sours.holograms.holo.configData;
import sours.holograms.holo.holoEVNT;

public class sHolograms extends JavaPlugin implements Listener{
	
	


	private static sHolograms instance;
	private configData configData;
	private configLang configLang;
	private static HashMap<String, Hologram> hologramsList;
	private holoEVNT holoEVNT;
	
    public sHolograms() {
    	instance = this;
    }
    public static sHolograms getInstance() {
        return instance;
    }
	
	

    
	@Override
	public void onEnable() {
		if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
			Bukkit.getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fHolograms will be disabled. &7Missing files: HolographicDisplays"));
			this.setEnabled(false);
			return;
		}
		
		hologramsList = new HashMap<>();
		
		instance = this;

		loadConfigs();
		Commands();
		Events();

		//Load holograms
		holoEVNT = new holoEVNT();
		holoEVNT.loadHolograms();
		
		this.saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fHolograms have been enbaled version " + instance.getDescription().getVersion()));	
	}
	
	
	@Override
	public void onDisable() {

		for(Hologram holograms : hologramsList.values()) {
			holograms.delete();
		}
		hologramsList.clear();
		loadConfigs();	
		this.saveDefaultConfig();
		
		
		Bukkit.getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fHolograms have been disabled version " + instance.getDescription().getVersion()));
	}
	

	private void loadConfigs() {
		configData configData = new configData();
		configData.setupData();
		configData.saveData();
		sours.holograms.untils.configLang configLang = new configLang();
		configLang.setupLang();
		configLang.saveLang();
	}
	

	private void Commands() {
		getCommand("test").setExecutor(new testCMD());
		getCommand("holograms").setExecutor(new hologramsCMD());

	}
	
	
	private void Events() {
		PluginManager pm = getServer().getPluginManager();
		this.getServer().getPluginManager().registerEvents(this, this);
		pm.registerEvents(new playerEVNT(), this);
	}
	
	
	public HashMap<String, Hologram> getHologramsList() {
		return this.hologramsList;
	}
	
}


