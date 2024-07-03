package me.yenu.arena.block;

import me.yenu.arena.Item.ItemManger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.SMOOTH_STONE;

public class BlockSpawn extends BukkitCommand {

    public BlockSpawn() {
        super(null);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player player) {

            player.getInventory().addItem(ItemManger.axe);
            player.getInventory().addItem(ItemManger.food);
            player.getInventory().addItem(ItemManger.Potion());
            player.getInventory().addItem(ItemManger.Potion());
            player.getInventory().addItem(ItemManger.Potion());
            player.getInventory().addItem(ItemManger.Potion());
            player.getInventory().addItem(ItemManger.Ppotion());
            player.getInventory().addItem(ItemManger.snowball);
            player.getInventory().addItem(ItemManger.icewall);
            player.getInventory().addItem(ItemManger.ga);

            Location location = player.getLocation().add(0,0,0);

            bs(location);
            wd(player);
            player.getInventory().addItem(ItemManger.Potion());

            return true;
        }
        return false;
    }



    public void bs(Location location) {
        World world = location.getWorld();

        for (int x = -15; x <= 14; x++) {
            for (int z = -15; z <= 14; z++) {
                Location location1 = location.clone().add(x, -1, z);

                for (int y = 0; y <= world.getMaxHeight(); y++) {
                    Location location2 = location1.clone().add(0, y, 0);
                    if (!(world.getBlockAt(location2).getType().equals(Material.AIR))) {
                        world.getBlockAt(location2).setType(Material.AIR);
                    }
                }
                world.getBlockAt(location1).setType(SMOOTH_STONE);
            }
        }
    }

    public void wd(Player player) {
        World world = player.getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(player.getLocation());
        worldBorder.setSize(29);
    }
}
