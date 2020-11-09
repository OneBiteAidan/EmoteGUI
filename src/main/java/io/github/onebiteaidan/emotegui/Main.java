package io.github.onebiteaidan.emotegui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener
{
    public Inventory inv;

    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
        createInv();
        saveDefaultConfig();
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (label.equalsIgnoreCase("emote"))
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage("Usable by players only");
                return true;
            }
            Player player = (Player) sender;
            player.openInventory(inv);
            return true;
        }
        return false;
    }

    @EventHandler()
    public void onClick(InventoryClickEvent event)
    {
        if (!event.getInventory().equals(inv))
        {
            return;
        }
        //Next 3 lines check to make sure the player cannot edit the inventory in any way
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        ConfigurationSection configSection = getConfig().getConfigurationSection("emotes"); //Defines which section of the config I want to enter
        for(String key : configSection.getKeys(false)) //Accessing the configuration section
        {

        }



        switch(event.getSlot())
        {
            case 0:
                clickCommand emote0 = new clickCommand("yonk", 0, player);
                emote0.chat();
                break;
        }


        if (event.getSlot() == 1)
        {
            player.chat("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: *ヽ(◕ヮ◕ヽ)");
        }
        if (event.getSlot() == 2)
        {
            player.chat("ɿ(｡･ɜ･)ɾⓌⓗⓨ?");
        }
        if (event.getSlot() == 3)
        {
            player.chat("ɿ(｡･ɜ･)ɾⓌⓗⓐⓣ？");
        }
        if (event.getSlot() == 4)
        {
            player.chat("ᕙ(⇀‸↼‶)ᕗ");
        }
        if (event.getSlot() == 5)
        {
            player.chat("☜(˚▽˚)☞");
        }
        if (event.getSlot() == 6)
        {
            player.chat("☜(⌒▽⌒)☞");
        }
        if (event.getSlot() == 7)
        {
            player.chat("(╯°□°）╯︵ ┻━┻");
        }
        if (event.getSlot() == 8)
        {
            player.closeInventory();
        }

    }

    public class clickCommand
    {
        String emote;
        Integer slot;
        Player player;

        clickCommand(String _emote, Integer _slot, Player _player)
        {
            emote = _emote;
            slot = _slot;
            player = _player;
        }

        void chat()
        {
            player.chat(emote);
        }

    }

    public void createInv() //Defining the GUI and also adding all of the objects inside of it
    {
        inv = Bukkit.createInventory(null, 9, "Emotes");

        ItemStack item = new ItemStack(Material.PAPER); //Defines the item
        ItemMeta meta = item.getItemMeta(); //Starts the item MetaData

        //First Emote
        meta.setDisplayName(ChatColor.AQUA + "ʕ•ᴥ•ʔ"); //Set name of item
        List<String> lore = new ArrayList<String>(); //Starts a list of lines to add to the lore value of the item
        lore.add(ChatColor.DARK_PURPLE + "Click me to send this reaction"); //One line of lore
        meta.setLore(lore); //Adds the lore to the item
        item.setItemMeta(meta); //Adds the metaData to the item
        inv.setItem(0, item); //Defines the item position in the GUI. Remember, slot one = 0, slot two = 1, etc...

        //Second Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: *ヽ(◕ヮ◕ヽ)");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        //Third Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "ɿ(｡･ɜ･)ɾⓌⓗⓨ?");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        //Fourth Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "ɿ(｡･ɜ･)ɾⓌⓗⓐⓣ？");
        item.setItemMeta(meta);
        inv.setItem(3, item);

        //Fifth Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "ᕙ(⇀‸↼‶)ᕗ");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        //Sixth Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "☜(˚▽˚)☞");
        item.setItemMeta(meta);
        inv.setItem(5, item);

        //Seventh Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "☜(⌒▽⌒)☞");
        item.setItemMeta(meta);
        inv.setItem(6, item);

        //Seventh Emote
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.AQUA + "(╯°□°）╯︵ ┻━┻");
        item.setItemMeta(meta);
        inv.setItem(7, item);

        //close button
        item.setType(Material.BARRIER);
        meta.setDisplayName(ChatColor.RED + "Close Menu");
        lore.clear();
        lore.add(ChatColor.GRAY + "Click to close menu");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(8, item);


    }
}
