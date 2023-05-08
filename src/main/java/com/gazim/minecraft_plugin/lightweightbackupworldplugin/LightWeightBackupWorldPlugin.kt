package com.gazim.minecraft_plugin.lightweightbackupworldplugin;

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupCommand
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupCommandExecutor
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupListExecutor
import com.github.shynixn.mccoroutine.bukkit.minecraftDispatcher
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin

class LightWeightBackupWorldPlugin : JavaPlugin() {

    val backupCommand = BackupCommand(this)

    override fun onEnable() {
        backupCommand.start()
    }

    override fun onDisable() {
        runBlocking {
            backupCommand.stop()
        }
    }

}
