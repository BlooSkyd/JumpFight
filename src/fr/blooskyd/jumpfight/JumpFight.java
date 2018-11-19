package fr.blooskyd.jumpfight;

import fr.ziedelth.dataclient.utils.Games;
import fr.ziedelth.evolzapi.spigot.EvolzSpigot;
import fr.ziedelth.gameframework.GameBuilder;
import fr.ziedelth.gameframework.GameFramework;
import fr.ziedelth.gameframework.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class JumpFight extends JavaPlugin {
    private int taskID;
    private JSONObject globalSettings, settings;
    private Games game;
    private boolean isCustom = false, isEvent = false;

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
                this.isCustom = (boolean) this.globalSettings.get("is-custom");
                this.isEvent = (boolean) this.globalSettings.get("is-event");
                this.settings = (JSONObject) this.globalSettings.get("settings");

            }
        })
        GameBuilder gb = new GameBuilder();
        gb.setAutoRespawn(true);
        gb.setDeathLoots(false);
        gb.setCanInteractWithInventory(true);
        gb.setGameSeconds(900);
        gb.addToNoDamageCause(EntityDamageEvent.DamageCause.FALL);
        gb.setJoinMessage("§a" + ChatUtils.PLAYER_REGEX + "§7a rejoint la partie !");
        gb.setQuitMessage("§c" + ChatUtils.PLAYER_REGEX + "§7a quitté la partie !");
        GameFramework.setGameBuilder(gb);
    }
}