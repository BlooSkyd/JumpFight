package fr.blooskyd.jumpfight.runnables;

import fr.ziedelth.gameframework.utils.runnable.PlayerRunnable;
import fr.ziedelth.gameframework.utils.FrameworkPlayer;
import fr.ziedelth.gameframework.utils.ScoreboardUtils;

import org.bukkit.entity.Player;

public class PlayerJoin extends PlayerRunnable {
    @Override
    public void run(Player player) {
        FrameworkPlayer fp = FrameworkPlayer.get(player);
        
        /**
          *
          * Partie Scoreboard
          *
          **/
        ScoreboardUtils scoreboard = new ScoreboardUtils(player);
        // On créer le Scoreboard
        scoreboard.create("&c&lJumpFight");
        // On définit le nombre de lignes à 15
        int line = 15;
        scoreboard.setLine(line--, "&7-- Temps --");
        scoreboard.setLine(line--, "&7Fin: &f?"); // Ligne 14
        scoreboard.setLine(line--, "&0"); // Saut de ligne
        scoreboard.setLine(line--, "&7-- Infos --");
        // TODO Méthode pour calculer la distance
        scoreboard.setLine(line--, "&7Distance: &f?"); // Ligne 11
        // TODO Méthode pour calculer la place (Descendant) selon un chiffre (Distance)
        scoreboard.setLine(line--, "&7Place: &f#?"); // Ligne 10
        fp.setScoreboard(scoreboard);
    }
}
