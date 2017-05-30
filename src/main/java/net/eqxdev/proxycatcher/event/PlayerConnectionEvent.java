package net.eqxdev.proxycatcher.event;

import net.eqxdev.proxycatcher.ProxyCatcher;
import net.eqxdev.proxycatcher.util.ProxyUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by eqxDev on 30/05/2017.
 */
public class PlayerConnectionEvent implements Listener {

    @EventHandler
    public void onConnection(AsyncPlayerPreLoginEvent e) {
        if(ProxyUtil.get().onProxy(e.getAddress())) {
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            e.setKickMessage(ProxyCatcher.get().KICK);
        }
    }

}
