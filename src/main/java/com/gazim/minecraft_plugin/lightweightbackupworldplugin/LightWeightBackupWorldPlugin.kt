package com.gazim.minecraft_plugin.lightweightbackupworldplugin;

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupCommandService
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin

class LightWeightBackupWorldPlugin : JavaPlugin() {

    private val backupCommandService = BackupCommandService(this)

    override fun onEnable(): Unit =
        backupCommandService.start()


    override fun onDisable() = runBlocking {
        backupCommandService.stop()
    }

}
