package sours.holograms.untils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



import net.md_5.bungee.api.ChatColor;
import sours.holograms.sHolograms;


public class configLang {

	private sHolograms plugin = sHolograms.getInstance();

	
	// Default Lang
	
	private void defaultLang() {
		
		setupLang();
		getLang().set("no-perms", "&cNo Permissions.");
		getLang().set("player-only", "&cThis command is for players only.");
	
		List<String> holohelp = new ArrayList<String>();
		holohelp.add("&8&m---------------------------------");
		holohelp.add("&4&l* &c&lHolograms");
		holohelp.add("     &7by Sours version %ver%");
		holohelp.add("");
		holohelp.add(" &c/holograms create <name> <line>");
		holohelp.add(" &c/holograms delete <name>");
		holohelp.add("&8&m---------------------------------");

		
		getLang().set("holograms.help", holohelp);
		getLang().set("holograms.matchingName", "&c&l[Holograms] &fThere is a hologram currently alright with that name.");
		getLang().set("holograms.notMatch", "&c&l[Holograms] &fThere is no hologram that matches that name.");
		getLang().set("holograms.create.invalid", "&c&l[Holograms] &fInvalid format. &7/holograms create <name> <line>");
		getLang().set("holograms.create.created", "&c&l[Holograms] &fYou have successfully created the hologram &a%name%&f.");
		getLang().set("holograms.delete.invalid", "&c&l[Holograms] &fInvalid format. &7/holograms delete <name>");
		getLang().set("holograms.delete.deleted", "&c&l[Holograms] &fYou have successfully deleted the hologram &a%name%&f.");
		
		
		
		saveLang();
		
	}
	
	
	
	
	// -----------------------------------
	// Lang Config
	// -----------------------------------

	// Files & Configs

	public FileConfiguration LangCFG;
	public File LangFile;

	// creating setup

	public void setupLang() {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		LangFile = new File(plugin.getDataFolder(), "lang.yml");

		if (!LangFile.exists()) {
			try {
				LangFile.createNewFile();
				Bukkit.getServer().getConsoleSender()
						.sendMessage(c.f("&c&l[Holograms] &fLang file has been created."));
				LangCFG = YamlConfiguration.loadConfiguration(LangFile);
				defaultLang();
				
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(c.f("&c&l[Holograms] &fCould not create lang file."));
			}
		}

		LangCFG = YamlConfiguration.loadConfiguration(LangFile);

	}

	public FileConfiguration getLang() {
		return LangCFG;
	}

	public void saveLang() {
		try {
			LangCFG.save(LangFile);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fLang file has been saved."));
		}
	}

	public void reloadLang() {
		Bukkit.getServer().getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fLang file has been reloaded."));
		LangCFG = YamlConfiguration.loadConfiguration(LangFile);
	}
	
	
}
