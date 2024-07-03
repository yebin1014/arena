package me.yenu.arena;

import me.yenu.arena.Item.IceWall;
import me.yenu.arena.Item.SnowBall;
import me.yenu.arena.Stage.ArenaCommand;
import me.yenu.arena.Stage.End;
import me.yenu.arena.Stage.Round;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arena extends JavaPlugin {

    private Round currentRound;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new IceWall(this), this);
        getServer().getPluginManager().registerEvents(new SnowBall(), this);

        currentRound = new Round(this);
        getCommand("start").setExecutor(new ArenaCommand(this));

        getCommand("endgame").setExecutor(new End(currentRound));

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Round getCurrentRound() {
        return currentRound;
    }
}
