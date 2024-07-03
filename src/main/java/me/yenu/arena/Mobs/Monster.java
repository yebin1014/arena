package me.yenu.arena.Mobs;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Monster {

    public void spawnS(Player player) {
        Location location = player.getLocation().add(5, 0,0);

        if(sMob != null) {
            ActiveMob spadeSoldier = sMob.spawn(BukkitAdapter.adapt(location), 1);

            Entity entity = spadeSoldier.getEntity().getBukkitEntity();
        }
    }

    public void spawnH(Player player) {
        Location location1 = player.getLocation();

        if(hMob != null) {
            ActiveMob heartSoldier = hMob.spawn(BukkitAdapter.adapt(location1), 1);

            Entity entity = heartSoldier.getEntity().getBukkitEntity();
        }
    }



    public final MythicMob sMob = MythicBukkit.inst().getMobManager().getMythicMob("spadesoldier").orElse(null);
    public final MythicMob hMob = MythicBukkit.inst().getMobManager().getMythicMob("heartsoldier").orElse(null);


}
