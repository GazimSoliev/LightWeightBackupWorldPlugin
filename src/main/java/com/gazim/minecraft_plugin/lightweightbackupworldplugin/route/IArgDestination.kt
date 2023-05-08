package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

interface IArgDestination: IDestination<IArgDestination> {
    val arg: String
}