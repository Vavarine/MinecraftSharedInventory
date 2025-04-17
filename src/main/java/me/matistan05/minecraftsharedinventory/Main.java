package me.matistan05.minecraftsharedinventory;

import me.matistan05.minecraftsharedinventory.classes.SharedInventoryGame;
import me.matistan05.minecraftsharedinventory.classes.SharedInventoryPlayer;
import me.matistan05.minecraftsharedinventory.commands.SharedInventoryCommand;
import me.matistan05.minecraftsharedinventory.commands.SharedInventoryCompleter;
import me.matistan05.minecraftsharedinventory.listeners.EventListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginCommand("sharedinventory").setExecutor(new SharedInventoryCommand(this));
        getCommand("sharedinventory").setTabCompleter(new SharedInventoryCompleter(this));
        new EventListeners(this);
        new Metrics(this, 21883);
        System.out.println("*********************************************************\n" +
                "Thank you for using this plugin! <3\n" +
                "Author: Matistan\n" +
                "If you enjoy this plugin, please rate it on spigotmc.org:\n" +
                "https://www.spigotmc.org/resources/shared-inventory.109491/\n" +
                "*********************************************************");

        // new BukkitRunnable() {
        // @Override
        // public void run() {
        // for (Player playerObject : Bukkit.getOnlinePlayers()) {
        // Player player = Bukkit.getPlayerExact(playerObject.getName());
        // if (player == null)
        // continue;
        // try {
        // player.getInventory().getItemInMainHand()
        // .setDurability((short)
        // (player.getInventory().getItemInMainHand().getDurability() + 1));
        // } catch (Exception ignored) {

        // }
        // // Damageable heldItemMeta = (Damageable) heldItem.getItemMeta();

        // // try {
        // // if(heldItemMeta == null) continue;
        // // if(!heldItemMeta.hasDamage()) continue;
        // //
        // // heldItemMeta.setDamage(heldItemMeta.getDamage() - 1);
        // //
        // // playersMessage(ChatColor.AQUA + "change player " + player.getName() + "
        // item
        // // " + heldItem.getType().name() + " durability");
        // //
        // // heldItem.setItemMeta((ItemMeta) heldItemMeta);
        // // } catch (Exception e) {
        // // // print
        // // e.printStackTrace();
        // // }
        // }
        // }
        // }.runTaskTimer(this, 0, 10);
    }

    private void playersMessage(String message) {
        for (Player playerObject : Bukkit.getOnlinePlayers()) {
            Player player = Bukkit.getPlayerExact(playerObject.getName());
            if (player == null)
                continue;
            player.sendMessage(message);
        }
    }

    @Override
    public void onDisable() {
        for (SharedInventoryGame game : SharedInventoryCommand.games.values()) {
            game.reset();
        }
    }
}
