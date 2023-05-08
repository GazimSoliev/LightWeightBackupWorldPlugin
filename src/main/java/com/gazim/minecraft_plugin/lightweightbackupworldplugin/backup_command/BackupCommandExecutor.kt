package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension.sendStrMessage
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgRoute
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.IArgRoute
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.defineAction
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.world_git_backup.GitWorldBackup
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.world_git_backup.WorldType
import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

class BackupCommandExecutor(private val plugin: Plugin, private val route: IArgRoute) : SuspendingCommandExecutor {

    private val saveJob = HashMap<WorldType, Job>()
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when (route.defineAction(args.toList())) {
            BackupActions.ArgsError -> sender.sendStrMessage("Args error")
            BackupActions.SaveWorld -> save(sender, WorldType.World)
            BackupActions.SaveWorldNether -> save(sender, WorldType.NetherWorld)
            BackupActions.SaveWorldTheEnd -> save(sender, WorldType.TheEndWorld)
            BackupActions.ListWorld -> list(sender, WorldType.World)
            BackupActions.ListWorldNether -> list(sender, WorldType.NetherWorld)
            BackupActions.ListWorldTheEnd -> list(sender, WorldType.TheEndWorld)
        }
        return true
    }

    fun save(sender: CommandSender, worldType: WorldType) =
        if (saveJob[worldType]?.isActive == true) sender.sendStrMessage("It's already in progress, await")
        else saveJob[worldType] = plugin.launch(Dispatchers.IO) {
            sender.sendStrMessage("Backup is started")
            GitWorldBackup.save(worldType = worldType)
            sender.sendStrMessage("Backup is completed")
        }

    fun list(sender: CommandSender, worldType: WorldType) =
        sender.sendStrMessage(
            GitWorldBackup.list(worldType)
                .reversed().joinToString(separator = "\n")
        )

    suspend fun awaitJobs() =
        saveJob.values.forEach { it.join() }

}