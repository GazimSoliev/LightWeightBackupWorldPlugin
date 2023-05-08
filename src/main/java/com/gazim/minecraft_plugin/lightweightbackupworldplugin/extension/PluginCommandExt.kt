package com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension

import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import kotlin.coroutines.CoroutineContext


//fun PluginCommand.setSuspendingExecutor(
//    context: CoroutineContext,
//    suspendingCommandExecutor: SuspendingCommandExecutorExt
//) = setSuspendingExecutor(context, object : com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor {
//    override suspend fun onCommand(
//        sender: CommandSender,
//        command: Command,
//        label: String,
//        args: Array<out String>
//    ) = suspendingCommandExecutor
//        .onCommand(sender, command, label, args)
//})
//
//fun interface SuspendingCommandExecutorExt {
//    suspend fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
//}


fun CommandSender.sendStrMessage(message: String) {
    val miniMessage = MiniMessage.miniMessage()
    val parsed = miniMessage.deserialize(message)
    this.sendMessage(parsed)
}