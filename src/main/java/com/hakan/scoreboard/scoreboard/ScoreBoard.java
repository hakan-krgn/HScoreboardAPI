package com.hakan.scoreboard.scoreboard;

import com.hakan.scoreboard.scoreboard.nms.SetupNMS;
import org.bukkit.entity.Player;

import java.util.Map;

public class ScoreBoard {

    private final Player player;
    private final Map<Integer, String> lines;
    private final SetupNMS.ScoreboardPacket scoreboardPacket;
    private String title;

    public ScoreBoard(Player player, String title, Map<Integer, String> lines) {
        this.player = player;
        this.title = title;
        this.lines = lines;
        this.scoreboardPacket = SetupNMS.scoreboardPacket;
    }

    public void open() {
        Player player = getPlayer();
        if (!player.isOnline()) return;

        close();
        scoreboardPacket.createObjectivePacket(player, 0, getTitle());
        scoreboardPacket.createObjectiveSlotPacket(player);
        for (Map.Entry<Integer, String> entry : getLines().entrySet()) {
            scoreboardPacket.createScorePacket(player, entry.getValue(), entry.getKey(), "CHANGE");
        }
    }

    public void close() {
        scoreboardPacket.createObjectivePacket(getPlayer(), 1, null);
    }

    public void setLine(int index, String line) {
        removeLine(index);
        this.lines.put(index, line);
        scoreboardPacket.createScorePacket(getPlayer(), line, index, "CHANGE");
    }

    public void removeLine(int index) {
        String indexLine = getLines().get(index);
        if (indexLine != null) {
            scoreboardPacket.createScorePacket(getPlayer(), indexLine, index, "REMOVE");
        }
        this.lines.remove(index);
    }

    public void removeLine(String line) {
        for (Map.Entry<Integer, String> entry : getLines().entrySet()) {
            if (entry.getValue().equals(line)) {
                removeLine(entry.getKey());
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        open();
    }

    public Map<Integer, String> getLines() {
        return this.lines;
    }
}