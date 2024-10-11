package org.reuac.lootlimiter;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
            List<String> sounds = getConfig().getStringList("Sound.sounds");
            MainListener.soundInterval = getConfig().getInt("Sound.interval");
            MainListener.soundVolume = getConfig().getInt("Sound.volume");
            MainListener.soundPitch = getConfig().getInt("Sound.pitch");

            for (String soundName : sounds) {
                try {
                    MainListener.Sounds.add(Sound.valueOf(soundName));
                } catch (IllegalArgumentException e) {
                    getLogger().severe("Invalid sound name: " + soundName);
                    MainCommand.hasError = true;
                }
            }
        }else {MainListener.soundEnabled = false;}

        if (getConfig().getBoolean("spawnParticle.enable")){
            MainListener.ParticleEnabled = true;
            MainListener.offsetX = getConfig().getInt("spawnParticle.offsetX");
            MainListener.offsetY = getConfig().getInt("spawnParticle.offsetY");
            MainListener.offsetZ = getConfig().getInt("spawnParticle.offsetZ");
            MainListener.particleCount = getConfig().getInt("spawnParticle.count");
            MainListener.particleInterval = getConfig().getInt("spawnParticle.interval");
            MainListener.extra = getConfig().getDouble("spawnParticle.extra");
            MainListener.particleX = getConfig().getInt("spawnParticle.revise.X");
            MainListener.particleY = getConfig().getInt("spawnParticle.revise.Y");
            MainListener.particleZ = getConfig().getInt("spawnParticle.revise.Z");
            List<String> particles = getConfig().getStringList("spawnParticle.particles");
            for (String particle : particles) {
                try {
                    MainListener.Particles.add(Particle.valueOf(particle));
                } catch (IllegalArgumentException e) {
                    getLogger().severe("Invalid particle name: " + particle);
                    MainCommand.hasError = true;
                }
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
    }
}
