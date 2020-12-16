package com.hakan.scoreboard.listeners;

import com.hakan.scoreboard.api.ScoreboardAPI;
import com.hakan.scoreboard.scoreboard.ScoreBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ScoreBoard scoreBoard = ScoreboardAPI.getScoreboard(event.getPlayer());
        if (scoreBoard != null) {
            scoreBoard.close();
        }
    }
}