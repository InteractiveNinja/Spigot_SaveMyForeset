package eu.imninja;

import org.bukkit.plugin.java.JavaPlugin;

public class SaveMyForest extends JavaPlugin implements SMFTexts {


    private int delay = 5;

    @Override
    public void onEnable() {
        System.out.println(HANDLE + " is enabled");

        this.saveDefaultConfig();
        this.delay = this.getConfig().getInt("delay");

        Runner r = new Runner(this);

        r.runTaskTimer(this, this.delay * 20L, this.delay * 20L);
    }

    @Override
    public void onDisable() {
        System.out.println(HANDLE + " is disabled");
    }


}
