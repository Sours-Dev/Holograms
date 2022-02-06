package sours.holograms.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sours.holograms.untils.messagesUtil;
import sours.holograms.untils.c;
import sours.holograms.untils.configLang;
import sours.holograms.holo.holoEVNT;
public class hologramsCMD implements CommandExecutor{

	/*
	 * 
	 * 	COmmans
	 *  /hologram create <name> <line>
	 *  /hologram delete <name>
	 * 
	 * 
	 * 
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	
	private configLang configLang = new configLang();
	private messagesUtil msgUtil = new messagesUtil();
	private holoEVNT holoEVNT = new holoEVNT();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		configLang.setupLang();
		if(!(sender instanceof Player)) {
			msgUtil.playerOnly();
			return true;
		}
		Player p = (Player) sender;
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("toggle")) {
				
				return true;
			}
		}
		
		if(!p.hasPermission("sholograms.admin")) {
			msgUtil.noPemrs(p);
			return true;
		}
		
		if(args.length == 0) {
			msgUtil.helpMSG(p);
			return true;
		}
		
		
		if(args[0].equalsIgnoreCase("create")) {
			if(args.length < 3) {
				configLang.setupLang();
				p.sendMessage(c.f(configLang.getLang().getString("holograms.create.invalid")));
				return true;
			}
			
			if(holoEVNT.sameName(args[1])) {
				configLang.setupLang();
				p.sendMessage(c.f(configLang.getLang().getString("holograms.matchingName")));
				return true;
			}
			
			String line = "";
			
			for(int i = 2; i<args.length; i ++) {
				line = line + " " + args[i];
			}
			
			holoEVNT.createHologram(args[1], p.getLocation(), line);

			String msg = configLang.getLang().getString("holograms.create.created");
			p.sendMessage(c.f(msg.replaceAll("%name%", args[1])));
			
			return true;
		}
		
		if(args[0].equalsIgnoreCase("delete")) {
			if(args.length != 2) {
				configLang.setupLang();
				p.sendMessage(c.f(configLang.getLang().getString("holograms.delete.invalid")));
				return true;
			}
			
			if(!holoEVNT.sameName(args[1])) {
				configLang.setupLang();
				p.sendMessage(c.f(configLang.getLang().getString("holograms.notMatch")));
				return true;
			}
			holoEVNT.deleteHologram(args[1]);
			String msg = configLang.getLang().getString("holograms.delete.deleted");
			p.sendMessage(c.f(msg.replaceAll("%name%", args[1])));
			return true;

		}
		
		return true;
	}

	
	
}
