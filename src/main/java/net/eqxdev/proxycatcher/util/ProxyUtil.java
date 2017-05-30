package net.eqxdev.proxycatcher.util;

import net.eqxdev.proxycatcher.ProxyCatcher;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by eqxDev on 30/05/2017.
 */
public class ProxyUtil {

    private static ProxyUtil proxyUtil;

    public static ProxyUtil get() {
        if(proxyUtil == null) {
            proxyUtil = new ProxyUtil();
        }
        return proxyUtil;
    }


    private final String USER_AGENT = "Mozilla/5.0";


    public boolean onProxy(InetAddress ip) {
        // create url
        String url = "http://check.getipintel.net/check.php?ip="+ ip.getHostAddress() +"&contact="+ ProxyCatcher.get().EMAIL +"&format=json&flags=m";
                try {
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setRequestMethod("GET");

                    con.setRequestProperty("User-Agent", USER_AGENT);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject jsonObject = new JSONObject(response.toString());
                    return jsonObject.get("status").equals("success") && !jsonObject.get("result").equals("0");
                } catch (IOException e) {
                    e.printStackTrace();
                }

        return false;
    }

}
