package com.hakan.scoreboard.scoreboard.nms.sbmethods;

import com.hakan.scoreboard.scoreboard.nms.SetupNMS;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static com.hakan.scoreboard.utils.FieldUtil.setField;

public class Packets_v1_8_R3 implements SetupNMS.ScoreboardPacket {

    public void createObjectivePacket(Player player, int mode, String displayName) {
        PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective();
        setField(packet, "a", player.getName());
        setField(packet, "d", mode);
        if (mode == 0 || mode == 2) {
            setField(packet, "b", displayName);
            setField(packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        }
        sendPacket(player, packet);
    }

    public void createObjectiveSlotPacket(Player player) {
        PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective();
        setField(packet, "a", 1);
        setField(packet, "b", player.getName());
        sendPacket(player, packet);
    }

    public void createScorePacket(Player player, String line, int score, String enumScoreboardAction) {
        PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(line);
        setField(packet, "b", player.getName());
        setField(packet, "c", score);
        setField(packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.valueOf(enumScoreboardAction));
        sendPacket(player, packet);
    }

    private void sendPacket(Player p, Packet packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
}