package net.eqxdev.proxycatcher;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.eqxdev.proxycatcher.event.PlayerConnectionEvent;
import net.eqxdev.proxycatcher.util.ConfigManager;
import net.eqxdev.proxycatcher.util.ProxyUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by eqxDev on 30/05/2017.
 */
public class ProxyCatcher extends JavaPlugin {

    private static ProxyCatcher proxyCatcher;

    public static ProxyCatcher get() {
        return proxyCatcher;
    }

    public String EMAIL = "test@testemail.com";
    public String KICK = "Your IP has been flagged as a proxy.";

    @Override
    public void onEnable() {
        proxyCatcher = this;
        ConfigManager.load(proxyCatcher, "config.yml");

        EMAIL = ConfigManager.get("config.yml").getString("email");
        KICK = ChatColor.translateAlternateColorCodes('&', ConfigManager.get("config.yml").getString("message"));

        ProxyUtil.get();
        getServer().getPluginManager().registerEvents(new PlayerConnectionEvent(), proxyCatcher);
    }

    @Override
    public void onDisable() {
        proxyCatcher = null;

    }
}
