package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension.sendStrMessage
import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.revwalk.RevCommit
import java.io.File

class BackupListExecutor(private val plugin: Plugin) : SuspendingCommandExecutor {
    private var isActive = false
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (isActive) sender.sendStrMessage("Await")
        else {
            val targetDirectory = "./light_weight_backups/git_world"
            val git = Git.init().setDirectory(File(targetDirectory)).call()
            runCatching {
                git.log().call()
            }.onSuccess {
                it.joinToString(separator = "\n", transform = RevCommit::getFullMessage)
                    .let(sender::sendStrMessage)
            }
        }
        return true
    }
}