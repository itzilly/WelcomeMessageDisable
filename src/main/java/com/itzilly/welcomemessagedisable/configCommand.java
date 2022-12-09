package com.itzilly.welcomemessagedisable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class configCommand implements CommandExecutor {

    private WelcomeMessageDisable main;
    private String wmdChatPrefix =
                                    ChatColor.GRAY + "[" +
                                    ChatColor.RED + "WMD" +
                                    ChatColor.GRAY + "]" +
                                    ChatColor.RESET + ": " +
                                    ChatColor.YELLOW;

    public configCommand(WelcomeMessageDisable mainClass) {
        this.main = mainClass;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Permission Check
            if (!player.hasPermission("wmd.commands")) {
                player.sendMessage(wmdChatPrefix + ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            // Ensure correct command usage
            if (args.length == 0) {
                sendHelpMessage(player);
                return false;
            }

            // Check subcommands
            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("reloadConfig")) {
                reloadConfigSubCommand(player);
                return false;
            }

            // Catch subcommand errors
            if (args.length == 2) {
                String option = args[1];
                // joins true/false
                if (subCommand.equalsIgnoreCase("joins")) { joinsSubCommand(player, option); }
                // quits true/false
                else if (subCommand.equalsIgnoreCase("quits")) { quitsSubCommand(player, option); }
            } else {
                // Not enough args
                sendHelpMessage(player);
            }
        } else {
            // Non-player sent command
            sendHelpMessage();
        }
        return false;
    }

    private void reloadConfigSubCommand(Player player) {
        player.sendMessage(wmdChatPrefix + "Config Reloaded!");
        Bukkit.getLogger().info("Config Reloaded!");
    }

    private void joinsSubCommand(Player player, String option) {
        // Enable join messages
        // /wmd joins true
        if (option.equalsIgnoreCase("true")) {
            enableJoinMessage();
            player.sendMessage(wmdChatPrefix + "Default join messages have been enabled!");
        }

        // Disable join messages
        // /wmd joins false
        else if (option.equalsIgnoreCase("false")) {
            disableJoinMessage();
            player.sendMessage(wmdChatPrefix + "Default join messages have been disabled!");
        }
    }

    private void quitsSubCommand(Player player, String option) {
        // Enable quit messages
        // /wmd joins true
        if (option.equalsIgnoreCase("true")) {
            enableQuitMessage();
            player.sendMessage(wmdChatPrefix + "Default quit messages have been enabled!");
        }

        // Disable quit messages
        // /wmd joins false
        else if (option.equalsIgnoreCase("false")) {
            disableQuitMessage();
            player.sendMessage(wmdChatPrefix + "Default quit messages have been disabled!");
        }
    }

    private void sendHelpMessage() {
        Bukkit.getLogger().warning("WMD commands are only available in-game!");
    }

    private void sendHelpMessage(Player player) {
        player.sendMessage(" ");
        player.sendMessage(wmdChatPrefix + ChatColor.YELLOW + "Commands");
        player.sendMessage(wmdChatPrefix + ChatColor.YELLOW + "  > " + ChatColor.AQUA + "reloadConfig");
        player.sendMessage(wmdChatPrefix + ChatColor.YELLOW + "  > " + ChatColor.AQUA + "joins " + ChatColor.GREEN + "true" + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "false");
        player.sendMessage(wmdChatPrefix + ChatColor.YELLOW + "  > " + ChatColor.AQUA + "quits " + ChatColor.GREEN + "true" + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "false");
        player.sendMessage(" ");
    }

    private void disableJoinMessage() {
        main.getConfig().set("disable-join-message", true);
        Bukkit.getLogger().info("Disabled default join message!");
    }

    private void enableJoinMessage() {
        main.getConfig().set("disable-join-message", false);
        Bukkit.getLogger().info("Enabled default quit message!");
    }

    private void disableQuitMessage() {
        main.getConfig().set("disable-quit-message", true);
        Bukkit.getLogger().info("Disabled default quit message!");
    }


    private void enableQuitMessage() {
        main.getConfig().set("disable-quit-message", false);
        Bukkit.getLogger().info("Enabled default quit message!");
    }

}
