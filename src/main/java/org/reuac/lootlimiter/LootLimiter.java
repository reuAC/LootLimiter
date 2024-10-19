package org.reuac.lootlimiter;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static com.bekvon.bukkit.residence.api.ResidenceApi.getResidenceManager;

public final class LootLimiter extends JavaPlugin {
    public static String PluginName = "[LootLimiter] ";

    public static LootLimiter main;

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("lootlimiter").setExecutor(new MainCommand());
        Bukkit.getPluginManager().registerEvents(new MainListener(),this);

        saveDefaultConfig();
        loadConfig();

        main = this;
    }

    @Override
    public void onDisable() {

    }

    private String makeColor(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public void loadConfig(){
        List<String> itemNames = getConfig().getStringList("LimitItems");
        List<String> worldNames = getConfig().getStringList("LimitWorlds");
        MainListener.LimiterItems.clear();
        MainListener.LimiterWorlds.clear();
        MainListener.Messages.clear();
        MainListener.noLimitOnResidence = getConfig().getBoolean("noLimitOnResidence");


        if (getConfig().getBoolean("Messages.enable")){
            MainListener.messageEnabled = true;
            List<String> messagesTemp = getConfig().getStringList("Messages.details");
            for (String message : messagesTemp) {
                MainListener.Messages.add(makeColor(message));
            }
        }else {MainListener.messageEnabled = false;}

        if (getConfig().getBoolean("Title.enable")){
            MainListener.titleEnabled = true;
            MainListener.title = makeColor(getConfig().getString("Title.maintitle"));
            MainListener.subtitle = makeColor(getConfig().getString("Title.subtitle"));
            MainListener.fadeIn = getConfig().getInt("Title.fadeIn");
            MainListener.stay = getConfig().getInt("Title.stay");
            MainListener.fadeOut = getConfig().getInt("Title.fadeOut");
        }else {MainListener.titleEnabled = false;}

        if (getConfig().getBoolean("Sound.enable")){
            MainListener.soundEnabled = true;
            String sound = getConfig().getString("Sound.sound");
            MainListener.soundVolume = getConfig().getInt("Sound.volume");
            MainListener.soundPitch = getConfig().getInt("Sound.pitch");

            try {
                MainListener.Sound = Sound.valueOf(sound);
            } catch (IllegalArgumentException e) {
                getLogger().severe("Invalid sound name: " + sound);
                MainCommand.hasError = true;
            }

        }else {MainListener.soundEnabled = false;}

        if (getConfig().getBoolean("spawnParticle.enable")){
            MainListener.ParticleEnabled = true;
            MainListener.offsetX = getConfig().getInt("spawnParticle.offsetX");
            MainListener.offsetY = getConfig().getInt("spawnParticle.offsetY");
            MainListener.offsetZ = getConfig().getInt("spawnParticle.offsetZ");
            MainListener.particleCount = getConfig().getInt("spawnParticle.count");
            MainListener.extra = getConfig().getDouble("spawnParticle.extra");
            MainListener.particleX = getConfig().getInt("spawnParticle.revise.X");
            MainListener.particleY = getConfig().getInt("spawnParticle.revise.Y");
            MainListener.particleZ = getConfig().getInt("spawnParticle.revise.Z");
            String particle = getConfig().getString("spawnParticle.particle");

            try {
                MainListener.Particle = Particle.valueOf(particle);
            } catch (IllegalArgumentException e) {
                getLogger().severe("Invalid particle name: " + particle);
                MainCommand.hasError = true;
            }
        }else {MainListener.ParticleEnabled = false;}

        for (String itemName : itemNames) {
            try {
                Material material = Material.valueOf(itemName);
                MainListener.LimiterItems.add(material);
            } catch (IllegalArgumentException e) {
                getLogger().severe("Invalid item type: " + itemName);
                MainCommand.hasError = true;
            }
        }

        for (String worldName : worldNames) {
            World world = Bukkit.getWorld(worldName);

            if (world != null) {
                MainListener.LimiterWorlds.add(world);
            } else {
                getLogger().severe("Invalid world name: " + worldName);
                MainCommand.hasError = true;
            }
        }

        Plugin resPlug = getServer().getPluginManager().getPlugin("Residence");
        if (resPlug != null){
            MainListener.hasResidence = true;
            MainListener.ResManager = Residence.getInstance().getResidenceManager();
        }else {
            MainListener.hasResidence = false;
        }

    }
}
