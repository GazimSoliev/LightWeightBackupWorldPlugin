package com.gazim.minecraft_plugin.lightweightbackupworldplugin.backup_command

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.route.IAction

enum class BackupActions : IAction {
    ArgsError, SaveWorld, SaveWorldNether,
    SaveWorldTheEnd, ListWorld, ListWorldNether,
    ListWorldTheEnd
}