package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgDestination
import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.ArgRoute

object Constants {
    const val backupCommand = "backup"
    val route = ArgRoute(
        BackupActions.ArgsError,
        ArgDestination(
            "save",
            BackupActions.ArgsError,
            ArgDestination("world", BackupActions.SaveWorld),
            ArgDestination("world_nether", BackupActions.SaveWorldNether),
            ArgDestination("world_the_end", BackupActions.SaveWorldTheEnd)
        ),
        ArgDestination(
            "list",
            BackupActions.ArgsError,
            ArgDestination("world", BackupActions.ListWorld),
            ArgDestination("world_nether", BackupActions.ListWorldNether),
            ArgDestination("world_the_end", BackupActions.ListWorldTheEnd)
        )
    )
}