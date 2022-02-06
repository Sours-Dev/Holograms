package sours.holograms.untils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import sours.holograms.sHolograms;

public class messagesUtil {

	private configLang cL = new configLang();
	private sHolograms plugin = sHolograms.getInstance();
	
	public void noPemrs(Player p) {
		cL.setupLang();
		p.sendMessage(c.f(cL.getLang().getString("no-perms")));
	}
	
	public void playerOnly() {
		cL.setupLang();
		Bukkit.getConsoleSender().sendMessage(c.f(cL.getLang().getString("player-only")));
	}
	
	public void helpMSG(Player p) {
	//holograms.help
		
		cL.setupLang();
		
		List<String> msg = cL.getLang().getStringList("holograms.help");
		for(String line : msg) {
			p.sendMessage(c.f(line.replaceAll("%ver%", plugin.getDescription().getVersion())));
		}
		
	}
	

}
