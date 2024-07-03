package me.yenu.arena.block;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.SMOOTH_STONE;

public class BlockRemove extends BukkitCommand {

    public BlockRemove() {
        super(null);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player player) {
            player.getInventory().clear();


            Location location = player.getLocation().add(0,0,0);

            bs(location);
            wd(player);

            return true;
        }
        return false;
    }

    public void bs(Location location) {
        World world = location.getWorld();


        for (int y = -1; y <= 10; y++) {
            for (int x = -30; x <= 30; x++) {
                for (int z = -30; z <= 30; z++) {
                    Location location1 = location.clone().add(x, y, z);
                    Block block = location1.getBlock();
                    if (block.getType() == SMOOTH_STONE) {
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }

    public void wd(Player player) {
        World world = player.getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(player.getLocation());
        worldBorder.setSize(1000000);
    }
}

