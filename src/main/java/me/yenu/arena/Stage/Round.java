package me.yenu.arena.Stage;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import me.yenu.arena.Arena;
import me.yenu.arena.Item.ItemManger;
import me.yenu.arena.block.BlockRemove;
import me.yenu.arena.block.BlockSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Round {

    private final Arena arena;
    private int round;
    private int score;
    private Set<Entity> activeMobs;
    private BlockSpawn blockSpawn;
    private BlockRemove blockRemove;

    public Round(Arena arena) {
        this.arena = arena;
        this.round = 0;
        this.activeMobs = Collections.synchronizedSet(new HashSet<>());
        this.blockRemove = new BlockRemove();
        this.blockSpawn = new BlockSpawn();
    }

    public void start(Player player) {
        round = 0;
        activeMobs.clear();
        Location location = player.getLocation();
        blockSpawn.bs(location);
        blockSpawn.wd(player);
        player.getInventory().addItem(ItemManger.axe);
        player.getInventory().addItem(ItemManger.food);
        player.getInventory().addItem(ItemManger.icewall);
        player.getInventory().addItem(ItemManger.snowball);
        for(int i = 0; i < 4; i++) {
            player.getInventory().addItem(ItemManger.Potion());
        }
        player.getInventory().addItem(ItemManger.Ppotion());
        player.getInventory().addItem(ItemManger.ga);

        next(player);
    }

    public void stopArena(Player player) {
        Bukkit.getScheduler().cancelTask(score);
        activeMobs.forEach(Entity::remove);
        activeMobs.clear();
        player.getInventory().clear();

        Location location = player.getLocation();

        blockRemove.bs(location);
        blockRemove.wd(player);

        reset();

    }

    private void reset() {
        round = 0;
        score = 0;
    }

    public void next(Player player) {
        score= Bukkit.getScheduler().scheduleSyncDelayedTask(arena, () -> {
            if (activeMobs.isEmpty()) {
                round++;
                Bukkit.broadcastMessage("라운드 " + round);
                if (round <= 10) {
                    normal(player);
                }
            }
        }, 100);
    }

    private void spawnMythic(Player player, String mobName, int count, int level, Location location) {
         // 스페이드 병사
        MythicMob mythicMob1 = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        if (mythicMob1 != null) {
            for (int i = 0; i < count; i++) {
                ActiveMob activeMob = mythicMob1.spawn(BukkitAdapter.adapt(location), level);
                if (activeMob != null) {
                    Entity entity = activeMob.getEntity().getBukkitEntity();
                    activeMobs.add(entity);
                }
            }
        }
    }

    private void normal(Player player) {
        Location location = player.getLocation().add(5,0,5);

        int spade = 0;
        int heart = 0;
        int level = 1;

        if (round >= 1 && round <= 5) {
            spade = 2;
        } else if (round >= 6 && round <= 10) {
            spade = 2;
            heart = 1;
        }
        spawnMythic(player, "Spadesoldier", spade,level,location);
        spawnMythic(player, "Heartsoldier", heart,level,location);
    }

    private void hard(Player player) {

        Location location = player.getLocation().add(5,0,5);

        int spade = 0;
        int heart = 0;
        int level = 2;


        if (round >= 8) {
            level = 2;
        } else if (round >= 15) {
            level = 3;
        }

        if (round >= 1 && round <= 3) {
            spade = 2;
        } else if (round >= 4 && round <= 7) {
            spade = 2;
            heart = 1;
        } else if (round >= 8 && round <= 12) {
            spade = 2;
            heart = 2;
        } else if (round >= 13 && round <= 18) {
            spade = 2;
            heart = 3;
        } else if (round >= 17 && round <= 20) {
            spade = 3;
            heart = 3;
        }

        spawnMythic(player, "Spadesoldier", spade,level,location);
        spawnMythic(player, "Heartsoldier", heart,level,location);
    }

    @EventHandler
    public void MobDeath(MythicMobDeathEvent e) {
        Entity entity = e.getEntity();
        if (activeMobs.contains(entity)) {
            mobclear(entity);

            if (activeMobs.isEmpty()){
                Bukkit.getScheduler().runTask(arena, () -> {
                    normal(Bukkit.getPlayer(e.getKiller().getUniqueId()));
                });
            }
        }
    }

    public void mobclear(Entity entity) {
        activeMobs.remove(entity);
        entity.remove();
    }
}
