package me.callum.mcdarkrp.core;

import me.callum.mcdarkrp.engine.ChatFormatter;
import me.callum.mcdarkrp.jobs.JobManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DarkRP extends JavaPlugin implements Listener {

    private ChatFormatter chatFormatter;

    public void onEnable() {
        chatFormatter = new ChatFormatter(this);

        getServer().getPluginManager().registerEvents(this, this);

        JobManager manager = new JobManager(this);
    }

    public void onDisable() {

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        event.setCancelled(true);

        if(message.toLowerCase().startsWith(".w ")) {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.WHISPER, message.substring(3));
            return;
        } else if(message.toLowerCase().startsWith(".y ")) {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.YELL, message.substring(3));
            return;
        } else if(message.toLowerCase().startsWith("@")) {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.ADMIN_CHAT, message.substring(1));
            return;
        }  else if(message.toLowerCase().startsWith(".advert ")) {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.ADVERT, message.substring(8));
            return;
        } else if (message.toLowerCase().startsWith(".ooc ")) {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.OOC, message.substring(5));
            return;
        }else {
            chatFormatter.sendMessage(event.getPlayer(), me.callum.mcdarkrp.engine.Type.LOCAL, message);
        }
    }
}
