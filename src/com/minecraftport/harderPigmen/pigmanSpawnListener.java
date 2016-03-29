package com.minecraftport.harderPigmen;


import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class pigmanSpawnListener implements Listener {

    public static main plugin;

    public pigmanSpawnListener(main instance) {plugin = instance;}

    @EventHandler(priority = EventPriority.NORMAL)
    public boolean onPigmanSpawnEvent(CreatureSpawnEvent event) {
        int lightlev = event.getLocation().getBlock().getLightLevel();
        Entity e = event.getEntity();
        if(e.getType() == EntityType.PIG_ZOMBIE) {
            if (lightlev < 7) {
                    double randNum = Math.random() * plugin.swordChanceInt;
                    PigZombie pigman = (PigZombie) e;

                    //Check for speedChange
                switch(plugin.changeSpeed){
                    case(-1):
                        pigman.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2));
                        break;
                    case(0):
                        break;
                    case(1):
                        pigman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                        break;
                    case(2):
                        pigman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                        break;
                    case(3):
                        pigman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
                    default:
                        break;
                }

                    if (plugin.enableAnger) {
                        pigman.setAngry(true);
                    }
                    if (plugin.enableSwordChance && randNum >= 1) {
                        pigman.getEquipment().setItemInHand(null);
                    }
            } else {
                event.setCancelled(true);
            }
        }else{
            return false;
        }
        return false;
    }
}