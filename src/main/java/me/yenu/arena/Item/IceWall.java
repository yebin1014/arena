package me.yenu.arena.Item;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IceWall implements Listener {

    private final Plugin plugin;

    public IceWall(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void wall(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack handItem = e.getPlayer().getInventory().getItemInMainHand();
        if (!(handItem.getType() == Material.ICE)) return;
        ItemMeta itemMeta = handItem.getItemMeta();
        if (itemMeta == null || !itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "얼음벽")) return;
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        e.setCancelled(true);

        Location eyeLocation = player.getEyeLocation();
        @NotNull Vector direction = eyeLocation.getDirection().normalize();

        List<Location> wallblocks = new ArrayList<>();

        wallspawn(eyeLocation, direction, wallblocks);

        new BukkitRunnable() {
            @Override
            public  void run() {
                for (Location location : wallblocks) {
                    Block block = location.getBlock();
                    if (block.getType() == Material.BLUE_ICE) {
                        block.setType(Material.AIR);
                    }
                }
            }

        }.runTaskLater(plugin, 60);
    }

    private void wallspawn(Location location, Vector direction, List<Location> wallblocks) {
        World world = location.getWorld();

        Vector vector = new Vector(-direction.getZ(), 0, direction.getX()).normalize();

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                Location location1 = location.clone().add(vector.clone().multiply(x)).add(0, y,0);
                Block block = location1.getBlock();
                block.setType(Material.BLUE_ICE);
                wallblocks.add(location1);
            }
        }
    }
}
