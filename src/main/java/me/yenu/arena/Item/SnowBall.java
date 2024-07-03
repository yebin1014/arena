package me.yenu.arena.Item;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.awt.*;

public class SnowBall implements Listener {

    private Item SnowBall;

    @EventHandler
    public void snow(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() == Material.SNOWBALL) {
            e.getItemDrop().getWorld().getNearbyEntities(e.getItemDrop().getLocation(), 10,10,10).forEach(entity -> {
                if (entity instanceof LivingEntity && !(entity instanceof Player))  {
                    slow((LivingEntity) entity, 5);
                }
            } );
        }
    }

    private void slow(LivingEntity entity, int s) {
        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, s * 20, 10);
        entity.addPotionEffect(effect);

    }
}
