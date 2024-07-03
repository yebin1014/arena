package me.yenu.arena.Stage;

import me.yenu.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor {

    private final Arena arena;

    public ArenaCommand(Arena arena) {
        this.arena = arena;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (s.equalsIgnoreCase("start")) {
                Round round = arena.getCurrentRound();
                round.start(player);
                return true;
            }
        }
        return false;
    }
}
