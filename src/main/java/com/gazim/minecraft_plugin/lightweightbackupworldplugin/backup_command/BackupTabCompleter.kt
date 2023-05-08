package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.Constants.route
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.defineRoute
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class BackupTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> = route.defineRoute(args.toList())
}