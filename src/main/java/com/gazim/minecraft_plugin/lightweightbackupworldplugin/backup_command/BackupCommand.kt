package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupActions.*
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension.setSuspendingExecutor
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgDestination
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgRoute
import com.github.shynixn.mccoroutine.bukkit.minecraftDispatcher
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import org.bukkit.plugin.java.JavaPlugin

class BackupCommand(private val plugin: JavaPlugin) {
    val strCommand = "backup"
    val route = ArgRoute(
        ArgsError,
        ArgDestination(
            "save",
            ArgsError,
            ArgDestination("world", SaveWorld),
            ArgDestination("world_nether", SaveWorldNether),
            ArgDestination("world_the_end", SaveWorldTheEnd)
        ),
        ArgDestination(
            "list",
            ArgsError,
            ArgDestination("world", ListWorld),
            ArgDestination("world_nether", ListWorldNether),
            ArgDestination("world_the_end", ListWorldTheEnd)
        )
    )
    val commandExecutor = BackupCommandExecutor(plugin, route)

    fun start() = with(plugin) {
        getCommand(strCommand)?.let {
            it.setSuspendingExecutor(minecraftDispatcher, commandExecutor)
            it.tabCompleter = BackupTabCompleter(route)
        }
    }

    suspend fun stop() {
        commandExecutor.awaitJobs()
    }
}
