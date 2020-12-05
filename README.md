**Example Code:**

```java
public void createSB(Player player) {

    ScoreboardAPI.ScoreboardManager scoreboardManager = ScoreboardAPI.getManager();

    scoreboardManager.setPlayer(player);
    scoreboardManager.setTitle("Faction");
    scoreboardManager.setLine(15, "1");
    scoreboardManager.setLine(14, "2");
    scoreboardManager.setLine(13, "3");
    scoreboardManager.setLine(12, "4");
    scoreboardManager.setLine(11, "5");
    scoreboardManager.setLine(10, "6");
    scoreboardManager.setLine(9, "7");
    scoreboardManager.setLine(8, "8");
    scoreboardManager.setLine(7, "9");
    scoreboardManager.setLine(6, "10");
    scoreboardManager.setLine(5, "11");
    scoreboardManager.setLine(4, "12");
    scoreboardManager.setLine(3, "13");
    scoreboardManager.setLine(2, "14");
    scoreboardManager.setLine(1, "15");

    ScoreBoard scoreBoard = scoreboardManager.create();

    scoreBoard.open();

    final int[] m = {0};
    new BukkitRunnable() {
        @Override
        public void run() {
            scoreBoard.setLine(15, "1blue: " + m[0]);
            scoreBoard.setLine(14, "2blue: " + m[0]);
            scoreBoard.setLine(13, "3blue: " + m[0]);
            scoreBoard.setLine(12, "4blue: " + m[0]);
            scoreBoard.setLine(11, "5blue: " + m[0]);
            scoreBoard.setLine(10, "6blue: " + m[0]);
            scoreBoard.setLine(9, "7blue: " + m[0]);
            scoreBoard.setLine(8, "8blue: " + m[0]);
            scoreBoard.setLine(7, "9blue: " + m[0]);
            scoreBoard.setLine(6, "10blue: " + m[0]);
            scoreBoard.setLine(5, "11blue: " + m[0]);
            scoreBoard.setLine(4, "12blue: " + m[0]);
            scoreBoard.setLine(3, "13blue: " + m[0]);
            scoreBoard.setLine(2, "14blue: " + m[0]);
            scoreBoard.setLine(1, "15blue: " + m[0]);
            m[0]++;
        }
    }.runTaskTimer(instance, 20 * 5, 1);
}