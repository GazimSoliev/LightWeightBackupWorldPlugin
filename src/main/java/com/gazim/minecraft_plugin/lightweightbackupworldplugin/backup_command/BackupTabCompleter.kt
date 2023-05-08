package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgRoute
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.defineRoute
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class BackupTabCompleter(private val route: ArgRoute) : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): List<String>? = args?.toList()?.let(route::defineRoute)
}