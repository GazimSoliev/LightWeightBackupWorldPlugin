package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.BackupActions.*
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.Constants.backupCommand
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command.Constants.route
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension.sendStrMessage
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.defineAction
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.world_git_backup.GitWorldBackup
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.world_type.WorldType
import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

class BackupCommandExecutor(private val plugin: Plugin) : SuspendingCommandExecutor {

    private val jobs = HashMap<WorldType, Job>()
    private val jobContext = Dispatchers.IO + SupervisorJob()
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender.hasPermission(backupCommand)) when (route.defineAction(args.toList())) {
            ArgsError -> sender.sendStrMessage("Args error")
            SaveWorld -> save(sender, WorldType.World)
            SaveWorldNether -> save(sender, WorldType.NetherWorld)
            SaveWorldTheEnd -> save(sender, WorldType.TheEndWorld)
            ListWorld -> list(sender, WorldType.World)
            ListWorldNether -> list(sender, WorldType.NetherWorld)
            ListWorldTheEnd -> list(sender, WorldType.TheEndWorld)
        } else sender.sendStrMessage("You have not permission")
        return true
    }

    private fun save(sender: CommandSender, worldType: WorldType) =
        if (jobs[worldType]?.isActive == true) sender.sendStrMessage("It's already in progress, await")
        else jobs[worldType] = plugin.launch(jobContext) {
            sender.sendStrMessage("Backup is started")
            GitWorldBackup.save(worldType = worldType)
            sender.sendStrMessage("Backup is completed")
        }

    private fun list(sender: CommandSender, worldType: WorldType) =
        sender.sendStrMessage(
            GitWorldBackup.list(worldType)
                .reversed().joinToString(separator = "\n")
        )

    suspend fun awaitJobs() =
        jobs.values.forEach { it.join() }

}