package fr.blooskyd.jumpfight.runnables;

import fr.ziedelth.gameframework.GameFramework;
import fr.ziedelth.gameframework.utils.FrameworkPlayer;
import fr.ziedelth.gameframework.utils.PlayerUtils;
import fr.blooskyd.jumpfight.runnables.PlayerDeath;
import fr.blooskyd.jumpfight.JumpFight;
import fr.ziedelth.gameframework.utils.runnable.PlayerDeathRunnable;
import org.bukkit.entity.Player;


public class PlayerDeath implements PlayerDeathRunnable {
    private final JumpFight jumpFight;

    public PlayerDeath(JumpFight jumpFight) {
        this.jumpFight = jumpFight;
    }

    @Override
    public void run(Player player, Player killer) {
        FrameworkPlayer fp = FrameworkPlayer.get(player);
        // On actualise le nombre de morts sur le Scoreboard du joueur
        fp.getScoreboard().setLine(9, "&7Mort(s): &f" + fp.getDeaths());

        // On liste tous les joueurs un par un
        FrameworkPlayer.getPlayers().forEach(frameworkPlayer -> {
            // On actualise le 1er sur les éliminations sur le scoreboard du joueur
            frameworkPlayer.getScoreboard().setLine(6, "&61. &e" + (PlayerUtils.isNull(PlayerUtils.getHighestKills(), 1) ? "???" : PlayerUtils.getHighestKills().get(0).getKey().getPlayer().getName()));
            // On actualise le 2ème sur les éliminations sur le scoreboard du joueur
            frameworkPlayer.getScoreboard().setLine(5, "&62. &e" + (PlayerUtils.isNull(PlayerUtils.getHighestKills(), 2) ? "???" : PlayerUtils.getHighestKills().get(1).getKey().getPlayer().getName()));
            // On actualise le 3ème sur les éliminations sur le scoreboard du joueur
            frameworkPlayer.getScoreboard().setLine(4, "&63. &e" + (PlayerUtils.isNull(PlayerUtils.getHighestKills(), 3) ? "???" : PlayerUtils.getHighestKills().get(2).getKey().getPlayer().getName()));
        });

        // Si le tueur du joueur n'est pas nul
        if (killer != null) {
            // On lui ajoute un effet de régénération en fonction de son amélioration via le shop

            FrameworkPlayer fk = FrameworkPlayer.get(killer);
            // On actualise le nombre d'éliminations sur le Scoreboard du tueur
            fk.getScoreboard().setLine(10, "&7Tué(s): &f" + fk.getKills());

            if (!this.jumpFight.isCustom() && !this.jumpFight.isEvent())
                fr.ziedelth.evolzapi.spigot.utils.PlayerUtils.getPlayer(killer).addEvoCoins(.5);

            // Quand le timer est fini, la partie rapproche le world border
            // Puis quand il ne reste qu'un joueur avec une ou plusieurs vie, la partie se termine
            if (!this.oneShot.isCustom() && !this.oneShot.isEvent())
                fr.ziedelth.evolzapi.spigot.utils.PlayerUtils.getPlayer(killer).addEvoCoins(10);
            GameFramework.getGameUtils().startEnd();
        }


    }
}
