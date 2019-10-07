package me.yarocks.fortuneblocks;/**
 * Created by USER on 8/25/2014.
 */

        import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BlockBreakHandler implements Listener {

    private FortuneBlocks c;

    public BlockBreakHandler(FortuneBlocks core) {
        this.c = core;
    }
    boolean canBuild(org.bukkit.entity.Player player, org.bukkit.block.Block block){
        return c.getWorldGuard().canBuild(player,
                player.getLocation().getBlock().getLocation());

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Block block = e.getBlock();
        Player player = e.getPlayer();
        if (player.getItemInHand() != null) {
            ItemStack item = player.getItemInHand();
            if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                Random rand = new Random();
                int enchLevel = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int multiplier = rand.nextInt(enchLevel + 1 / 2) + 2; // Multiplier by enchantment level + 1 and doesn't go below 2
                List<String> fortuned = c.getConfig().getStringList("fortuned");
                if (!fortuned.isEmpty()) {
                    if (!e.isCancelled()) {
                        if (canBuild(player, block)) {
                            if (fortuned.contains(block.getType().name())) {
                                Collection<ItemStack> drops = block.getDrops();
                                for (ItemStack drop : drops) {
                                    for (int i = 0; i < multiplier; i++) {
                                        block.getWorld().dropItemNaturally(block.getLocation(), drop);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

    }
}

