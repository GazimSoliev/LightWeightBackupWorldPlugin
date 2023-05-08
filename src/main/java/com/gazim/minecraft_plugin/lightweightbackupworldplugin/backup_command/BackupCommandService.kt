package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.github.shynixn.mccoroutine.bukkit.minecraftDispatcher
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import org.bukkit.plugin.java.JavaPlugin

class BackupCommandService(private val plugin: JavaPlugin) {
    private val commandExecutor = BackupCommandExecutor(plugin)
    private val tabCompleter = BackupTabCompleter()

    fun start() = with(plugin) {
        getCommand(Constants.backupCommand)?.let {
            it.setSuspendingExecutor(minecraftDispatcher, commandExecutor)
            it.tabCompleter = tabCompleter
        }!!
    }

    suspend fun stop() =
        commandExecutor.awaitJobs()

}
