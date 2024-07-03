package me.yenu.arena.PlayerInfo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Player {

    private int highestround;
    private int totalround;
    private int vudrbs;

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public Player(int highestround, int totalround, int vudrbs) {
        this.highestround = highestround;
        this.totalround = totalround;
        this.vudrbs = vudrbs;
    }

    public int getHighestround() {
        return highestround;
    }

    public int getTotalround() {
        return totalround;
    }

    public double getvudrbs() {
        return vudrbs;
    }

    public static void main(String[] args) {
        Player player = new Player(5,10,3);
        String json = gson.toJson(player);

        Path path = Path.of("./data/player/player.json");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileWriter out = null;
        try {
            out = new FileWriter(path.toFile());
            out.write(json);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        FileReader in = null;
        try {
            in = new FileReader(path.toFile());
            JsonElement element =  JsonParser.parseReader(in);
            Player readPlayer = gson.fromJson(element, Player.class);
            System.out.println("최고 클리어 라운드 : " + readPlayer.getHighestround());
            System.out.println("총 플레이한 라운드 수 : " + readPlayer.getTotalround());
            System.out.println("평균 라운드 : " + readPlayer.getvudrbs());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
