package me.hbcommando.rpgmc.util;

import me.hbcommando.rpgmc.RpgMc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.logging.Level;

public class SpawnUtil implements Listener {
    private ConfigUtil config;
    private Location spawn;

    public SpawnUtil(RpgMc plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        config = new ConfigUtil(plugin, "spawn.yml");

        String worldName = config.getConfig().getString("world");
        double x = config.getConfig().getDouble("x");
        double y = config.getConfig().getDouble("y");
        double z = config.getConfig().getDouble("z");
        float yaw = (float) config.getConfig().getDouble("yaw");
        float pitch = (float) config.getConfig().getDouble("pitch");

        if (worldName != null) {
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Bukkit.getLogger().log(Level.SEVERE, "The world \""+ worldName +"\" does not exist");
                return;
            }

            spawn = new Location(world, x, y, z, yaw, pitch);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (spawn != null) {
            event.setRespawnLocation(spawn);
        }
    }

    public void teleport(Player player) {
        if (spawn == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe spawn is not set"));
            return;
        }

        player.teleport(spawn);
    }

    public void set(Location spawn) {
        this.spawn = spawn;

        String worldName = spawn.getWorld().getName();
        double x = spawn.getX();
        double y = spawn.getY();
        double z = spawn.getZ();
        float yaw = spawn.getYaw();
        float pitch = spawn.getPitch();

        config.getConfig().set("world", worldName);
        config.getConfig().set("", x);
        config.getConfig().set("", y);
        config.getConfig().set("", z);
        config.getConfig().set("", yaw);
        config.getConfig().set("", pitch);
        config.save();
    }
}
