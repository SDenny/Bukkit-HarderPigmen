package com.minecraftport.harderPigmen;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    public static main plugin;
    public final pigmanSpawnListener psl = new pigmanSpawnListener(this);

    double swordChanceInt = getConfig().getInt("swordChance");

    boolean enableAnger = getConfig().getBoolean("enableAnger");
    boolean enableSwordChance = getConfig().getBoolean("enableSwordChance");
    int changeSpeed = getConfig().getInt("changeSpeed");

    int utdConf = 2;//Version of MOST up to date configuration file

    public static String tag = "[HarderPigmen]";

    @Override
    public void onDisable() {
        PluginDescriptionFile desc = this.getDescription();
        System.out.println(tag + " v." + desc.getVersion() + " has been disabled!");
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this.psl, this);
        new pigmanSpawnListener(this);

        PluginDescriptionFile desc = this.getDescription();
        getConfig().options().copyDefaults(true);

        getConfig().options().header("See http://dev.bukkit.org/bukkit-plugins/harder-pigmen/ for configuration help!").copyHeader(true);

        checkConfigVer(getConfig().getInt("configVer"));

        saveConfig();

        System.out.println(tag + " v." + desc.getVersion() + " has been enabled!");
    }

    public void checkConfigVer(int ver){
        switch(ver){
            case(1):
                System.out.println(tag + " Your config is version 1, updating to most recent version.");
                getConfig().set("enableSlowness", null);//Removes enableSlowness line
                getConfig().addDefault("changeSpeed", 2);
                getConfig().set("configVer", 2);
                System.out.println(tag + " Config updated to version 2. Check for new configurable options!");
                break;
            default:
                System.out.println(tag + " Looks like your config is up to date.");
                break;
        }
        saveConfig();
        if(getConfig().getInt("configVer") != utdConf) {
            System.out.println("Looping back to check configuration again for any missed updates.");
            checkConfigVer(getConfig().getInt("configVer"));//Loops back to make sure everything has been changed to the most up to date version of the config.
        }
    }


}
