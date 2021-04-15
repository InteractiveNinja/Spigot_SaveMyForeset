package eu.imninja;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Runner extends BukkitRunnable {

    private final JavaPlugin plugin;
    List<Material> ignored = Arrays.asList(Material.AIR);

    List<Material> included = Arrays.asList(Material.GRASS_BLOCK, Material.OAK_SAPLING,Material.ACACIA_SAPLING,Material.BAMBOO_SAPLING,Material.BIRCH_SAPLING,
            Material.SPRUCE_SAPLING,Material.DARK_OAK_SAPLING, Material.JUNGLE_SAPLING);

    List<Material> placeable = Arrays.asList(Material.GRASS_BLOCK,Material.DIRT);
    public Runner(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        for (World w : plugin.getServer().getWorlds()) {

            for (Item i : w.getEntitiesByClass(Item.class)) {
                Block block = w.getBlockAt(i.getLocation());
                Material item = i.getItemStack().getType();
                if (isSapling(item) && checkIfPlantable(block)) {

                    block.setType(item);
                    i.remove();

                }
//
            }
        }

    }

    private boolean isSapling(Material i){
        return included.contains(i);
    }

    private boolean checkIfPlantable(Block b) {

        Material found = b.getRelative(BlockFace.DOWN).getType();

        return !ignored.contains(found) && placeable.contains(found);
    }
}
