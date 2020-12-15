package com.hakan.scoreboard.scoreboard.nms;

import com.hakan.scoreboard.scoreboard.nms.sbmethods.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetupNMS {

    public static ScoreboardPacket scoreboardPacket;

    public void setup() {
        String serverVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        System.out.println("sadsda");
        switch (serverVersion) {
            case "v1_8_R3":
                scoreboardPacket = new Packets_v1_8_R3();
                System.out.println(scoreboardPacket + "");
                break;
            case "v1_9_R1":
                scoreboardPacket = new Packets_v1_9_R1();
                break;
            case "v1_9_R2":
                scoreboardPacket = new Packets_v1_9_R2();
                break;
            case "v1_10_R1":
                scoreboardPacket = new Packets_v1_10_R1();
                break;
            case "v1_11_R1":
                scoreboardPacket = new Packets_v1_11_R1();
                break;
            case "v1_12_R1":
                scoreboardPacket = new Packets_v1_12_R1();
                break;
            case "v1_13_R1":
                scoreboardPacket = new Packets_v1_13_R1();
                break;
            case "v1_13_R2":
                scoreboardPacket = new Packets_v1_13_R2();
                break;
            case "v1_14_R1":
                scoreboardPacket = new Packets_v1_14_R1();
                break;
            case "v1_15_R1":
                scoreboardPacket = new Packets_v1_15_R1();
                break;
            case "v1_16_R1":
                scoreboardPacket = new Packets_v1_16_R1();
                break;
            case "v1_16_R2":
                scoreboardPacket = new Packets_v1_16_R2();
                break;
            case "v1_16_R3":
                scoreboardPacket = new Packets_v1_16_R3();
                break;
        }
    }

    public interface ScoreboardPacket {

        void createObjectivePacket(Player player, int mode, String displayName);

        void createObjectiveSlotPacket(Player player);

        void createScorePacket(Player player, String line, int score, String enumScoreboardAction);

    }
}