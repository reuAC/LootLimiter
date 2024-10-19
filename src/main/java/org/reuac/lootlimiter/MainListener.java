package org.reuac.lootlimiter;

import com.bekvon.bukkit.residence.Residence;
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
    public static Sound Sound;
    public static Particle Particle;

    public static boolean messageEnabled;
    public static boolean titleEnabled;
    public static boolean soundEnabled;
    public static boolean ParticleEnabled;

    public static String title;
    public static String subtitle;
    public static int fadeIn;
    public static int stay;
    public static int fadeOut;

    public static float soundVolume;
    public static float soundPitch;

    public static int particleCount;
    public static double offsetX;
    public static double offsetY;
    public static double offsetZ;
    public static double extra;
    public static double particleX;
    public static double particleY;
    public static double particleZ;

    public static boolean noLimitOnResidence;

    public static boolean hasResidence;
    public static com.bekvon.bukkit.residence.protection.ResidenceManager ResManager;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) {return;}
        if (!LimiterItems.contains(event.getBlock().getType())) {return;}
        if (!LimiterWorlds.contains(event.getBlock().getWorld())) {return;}
        if (noLimitOnResidence && hasResidence){
//            Location loc = event.getBlock().getLocation();
//            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
//            if(res!=null){return;}

            if (Residence.getInstance().getResidenceManager().getByLoc(event.getBlock().getLocation()) != null){return;}
        }
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
            playSoundsSequentially(player,Sound);
        }

        if (ParticleEnabled){
            Location location = event.getBlock().getLocation();
            location.setX(location.getX() + particleX);
            location.setY(location.getY() + particleY);
            location.setZ(location.getZ() + particleZ);
            spawnParticlesWithInterval(location);
        }
    }

    public void playSoundsSequentially(Player player, Sound sound) {
        Location location = player.getLocation();
        player.playSound(location, sound, soundVolume, soundPitch);
    }

    public static void spawnParticlesWithInterval(Location location) {
        World world = location.getWorld();
        world.spawnParticle(Particle,location,particleCount,offsetX,offsetY,offsetZ,extra);
    }
}
