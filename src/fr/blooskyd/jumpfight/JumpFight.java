package fr.blooskyd.jumpfight;

import fr.ziedelth.dataclient.utils.Games;
import fr.ziedelth.gameframework.GameBuilder;
import fr.ziedelth.gameframework.GameFramework;
import fr.ziedelth.gameframework.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JumpFight extends JavaPlugin {
    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this,() -> {
            this.globalSettings = EvolzSpigot.getSettings();

            if ( this.globalSettings != null ) {
                this.game = Games.getGameWithGameID(((Long) this.globalSettings.get ("name")).intValue());
                This.isCustom = (boolean) this.global
            }
        })
        GameBuilder gb = new GameBuilder();
        gb.setAutoRespawn(true);
        gb.setDeathLoots(false);
        gb.setCanInteractWithInventory(true);
        gb.setGameSeconds(900);
        gb.addToNoDamageCause(EntityDamageEvent.DamageCause.FALL);
        gb.setJoinMessage("§e" + ChatUtils.PLAYER_REGEX + "§7a rejoint la partie !");
        GameFramework.setGameBuilder(gb);
    }
}