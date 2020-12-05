package com.hakan.scoreboard.api;

import com.hakan.scoreboard.Main;
import com.hakan.scoreboard.scoreboard.ScoreBoard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardAPI {

    public static void setup(Plugin plugin) {
        Main.setup(plugin);
    }

    public static ScoreboardManager getManager() {
        return new ScoreboardManager();
    }

    public static class ScoreboardManager {

        private final Map<Integer, String> lines = new HashMap<>();
        private Player player;
        private String title = "";

        public void setPlayer(Player player) {
            this.player = player;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setLine(int index, String line) {
            this.lines.put(index, line);
        }

        public void addLine(String line) {
            this.lines.put(this.lines.size() + 1, line);
        }

        public void removeLine(String line) {
            for (Map.Entry<Integer, String> entry : this.lines.entrySet()) {
                if (entry.getValue().equals(line)) {
                    this.lines.remove(entry.getKey());
                }
            }
        }

        public void removeLine(int index) {
            this.lines.remove(index);
        }

        public ScoreBoard create() {
            return new ScoreBoard(this.player, this.title, this.lines);
        }
    }
}