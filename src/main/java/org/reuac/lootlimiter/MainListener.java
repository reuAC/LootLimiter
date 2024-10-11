package org.reuac.lootlimiter;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MainListener implements Listener {
    public static List<Material> LimiterItems = new ArrayList<>();
    public static List<World> LimiterWorlds = new ArrayList<>();
    public static List<String> Messages = new ArrayList<>();
    public static List<Sound> Sounds = new ArrayList<>();
    public static List<Particle> Particles = new ArrayList<>();

    public static boolean messageEnabled;
    public static boolean titleEnabled;
    public static boolean soundEnabled;
    public static boolean ParticleEnabled;

    public static String title;
    public static String subtitle;
    public static int fadeIn;
    public static int stay;
    public static int fadeOut;

    public static int soundInterval;
    public static float soundVolume;
    public static float soundPitch;

    public static int particleCount;
    public static int particleInterval;
    public static double offsetX;
    public static double offsetY;
    public static double offsetZ;
    public static double extra;
    public static double particleX;
    public static double particleY;
    public static double particleZ;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!LimiterItems.contains(event.getBlock().getType())) {return;}
        if (!LimiterWorlds.contains(event.getBlock().getWorld())) {return;}
        Player player = event.getPlayer();
        if (player.hasPermission("lootlimiter.bypass")) {return;}
        event.setDropItems(false);

        if (messageEnabled){
            for (String message : Messages) {
                player.sendMessage(message);
            }
        }

        if (titleEnabled){
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }

        if (soundEnabled){
            playSoundsSequentially(player,Sounds,soundInterval);
        }

        if (ParticleEnabled){
            Location location = event.getBlock().getLocation();
            location.setX(location.getX() + particleX);
            location.setY(location.getY() + particleY);
            location.setZ(location.getZ() + particleZ);
            spawnParticlesWithInterval(location);
        }

    }

    public void playSoundsSequentially(Player player, List<Sound> sounds, int interval) {
        Location location = player.getLocation();

        new BukkitRunnable() {
            private int index = 0;

            @Override
            public void run() {
                if (index < sounds.size()) {
                    player.playSound(location, sounds.get(index), soundVolume, soundPitch);
                    index++;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(LootLimiter.main, 0L, interval);
    }

    public static void spawnParticlesWithInterval(Location location) {
        World world = location.getWorld();
        new BukkitRunnable() {
            private int index = 0;

            @Override
            public void run() {
                if (index < Particles.size()) {
                    world.spawnParticle(Particles.get(index),location,particleCount,offsetX,offsetY,offsetZ,extra);
                    index++;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(LootLimiter.main, 0L, particleInterval);
    }
}
