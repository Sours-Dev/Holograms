package sours.holograms.holo;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


import net.md_5.bungee.api.ChatColor;
import sours.holograms.sHolograms;
import sours.holograms.untils.c;

public class configData {

	private sHolograms plugin = sHolograms.getInstance();

	// -----------------------------------
	// Data Config
	// -----------------------------------

	// Files & Configs

	public FileConfiguration DataCFG;
	public File DataFile;

	// creating setup

	public void setupData() {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		DataFile = new File(plugin.getDataFolder(), "Database.yml");

		if (!DataFile.exists()) {
			try {
				DataFile.createNewFile();
				Bukkit.getServer().getConsoleSender()
						.sendMessage(c.f("&c&l[Holograms] &fDatebase file has been created."));
				DataCFG = YamlConfiguration.loadConfiguration(DataFile);

			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(c.f("&c&l[Holograms] &fCould not create datebase file."));
			}
		}

		DataCFG = YamlConfiguration.loadConfiguration(DataFile);

	}

	public FileConfiguration getData() {
		return DataCFG;
	}

	public void saveData() {
		try {
			DataCFG.save(DataFile);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fDatabase file has been saved."));
		}
	}

	public void reloadData() {
		Bukkit.getServer().getConsoleSender().sendMessage(c.f("&c&l[Holograms] &fDatebase file has been reloaded."));
		DataCFG = YamlConfiguration.loadConfiguration(DataFile);
	}

	// -----------------------------------
	// end of Data Config
	// -----------------------------------

}
