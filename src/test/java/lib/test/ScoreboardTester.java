package lib.test;

import net.gabriel.internal.library.com.InternalLib;
import net.gabriel.internal.library.com.scoreboard.InternalScoreboard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardTester {

    public void applyScoreboard(Player player) {
        // The lines of the scoreboard
        List<String> lines = new ArrayList<>();
        lines.add("");
        lines.add("§fHello, i'm a line!");
        lines.add("§eSorry to waste your time!");

        InternalScoreboard scoreboard = new InternalScoreboard("§3§lTitle", lines, player);

        // Sets the scoreboard updating delay, i put 40 ticks (2 seconds)
        scoreboard.setUpdatingDelay(InternalLib.getInstance(), 40L);
        // Finnaly applies the scoreboard to a player.
        scoreboard.showTo();
    }

}
