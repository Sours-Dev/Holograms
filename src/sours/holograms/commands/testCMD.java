package sours.holograms.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import sours.holograms.sHolograms;
import sours.holograms.holo.holoEVNT;


public class testCMD implements CommandExecutor {

	private sHolograms plugin = sHolograms.getInstance();
	private holoEVNT holoEVNT = new holoEVNT();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("t")) {
				holoEVNT.test1(p);
				return true;
			}
		}
		
		holoEVNT.test(p);
		

		return true;
	}

}
