package me.hbcommando.rpgmc;

import me.hbcommando.rpgmc.commands.Fly;
import me.hbcommando.rpgmc.commands.SetSpawn;
import me.hbcommando.rpgmc.commands.Spawn;
import me.hbcommando.rpgmc.util.ConfigUtil;
import me.hbcommando.rpgmc.util.SpawnUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RpgMc extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Started");
        saveDefaultConfig();

        List<String> kitItems = (List<String>) getConfig().getList("kit");
        for (String itemName : kitItems) {
            getLogger().info(itemName);
        }

        ConfigUtil config = new ConfigUtil(this, "test.yml");
        config.getConfig().set("hello", "world");
        config.save();

        SpawnUtil spawnUtil = new SpawnUtil(this);

        getCommand("fly").setExecutor(new Fly());
        getCommand("spawn").setExecutor(new Spawn(spawnUtil));
        getCommand("setspawn").setExecutor(new SetSpawn(spawnUtil));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin shutting");
    }
}
