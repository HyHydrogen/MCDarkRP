package me.callum.mcdarkrp.engine;

import me.callum.mcdarkrp.core.DarkRP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ChatFormatter {

    private String prefix;

    private DarkRP plugin;

    public ChatFormatter(DarkRP plugin) {
        this.plugin = plugin;
        this.prefix = "";
    }

    public void privateMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', (prefix + message)));
    }

    public void sendMessage(Player player, Type type, String message) {
        String receiveNode = getPermission(type);
        String chatFormat = getFormat(type);
        String messageToSend = chatFormat;

        messageToSend = messageToSend.replaceAll("<player>", player.getDisplayName());
        messageToSend = messageToSend.replaceAll("<message>", message);

        if(getRange(type) == -1) {
            for(Player loopPlayer : plugin.getServer().getOnlinePlayers()) {

                if (receiveNode != null && !loopPlayer.hasPermission(receiveNode)) {
                    continue;
                }

                privateMessage(loopPlayer, messageToSend);
            }
            return;
        }

        for (Entity loopEntity : player.getNearbyEntities(getRange(type), getRange(type), getRange(type))) {

            if(!(loopEntity instanceof Player)) {
                continue;
            }

            Player loopPlayer = (Player) loopEntity;

            if (receiveNode != null && !loopPlayer.hasPermission(receiveNode)) {
                continue;
            }

            privateMessage(loopPlayer, messageToSend);
        }

        privateMessage(player, messageToSend);
    }

    private int getRange(Type type) {
        int returnValue = 0;
        switch (type) {
            case LOCAL: returnValue = 14; break;
            case WHISPER: returnValue = 3;break;
            case YELL: returnValue = 10; break;
            case OOC: returnValue = -1; break;
            case ADVERT: returnValue = -1; break;
            case ADMIN_CHAT: returnValue = -1; break;
            default: break;
        }
        return returnValue;
    }

    private String getFormat(Type type) {
        String returnValue = "";
        switch (type) {
            case LOCAL: returnValue = "&6[&cLocal&6] &c<player>&7: &f<message>"; break;
            case WHISPER: returnValue = "&6[&cWhisper&6] &c<player>&7: &f<message>";break;
            case YELL: returnValue = "&6[&cYell&6] &c<player>&7: &f<message>";break;
            case OOC: returnValue = "&6[&cOOC&6] &c<player>&7: &f<message>";break;
            case ADVERT: returnValue = "&6[&Advert&6] &c<player>&7: &f<message>";break;
            case ADMIN_CHAT: returnValue = "&6[&cAdmin Chat&6] &c<player>&7: &f<message>";break;
            default: break;
        }
        return returnValue;
    }

    private String getPermission(Type type) {
        String returnValue = "";
        switch (type) {
            case LOCAL: returnValue = null; break;
            case WHISPER: returnValue = null; break;
            case YELL: returnValue = null; break;
            case OOC: returnValue = null; break;
            case ADVERT: returnValue = null; break;
            case ADMIN_CHAT: returnValue = "chat.admin" ;break;
            default: break;
        }
        return returnValue;
    }
}
