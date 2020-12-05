package com.hakan.scoreboard.sb;

import com.hakan.scoreboard.Main;
import com.hakan.scoreboard.sb.nms.SetupNMS;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import org.bukkit.Bukkit;
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
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            Player player = getPlayer();
            if (!player.isOnline()) return;

            scoreboardPacket.createObjectivePacket(getPlayer(), 1, null);
            scoreboardPacket.createObjectivePacket(player, 0, getTitle());
            scoreboardPacket.createObjectiveSlotPacket(player);
            for (Map.Entry<Integer, String> entry : getLines().entrySet()) {
                scoreboardPacket.createScorePacket(player, entry.getValue(), entry.getKey(), PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE.name());
            }
        });
    }

    public void close() {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> scoreboardPacket.createObjectivePacket(getPlayer(), 1, null));
    }

    public void setLine(int index, String line) {
        removeLine(index);
        this.lines.put(index, line);
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> scoreboardPacket.createScorePacket(getPlayer(), line, index, PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE.name()));
    }

    public void removeLine(int index) {
        String indexLine = getLines().get(index);
        if (indexLine != null) {
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> scoreboardPacket.createScorePacket(getPlayer(), indexLine, index, PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE.name()));
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