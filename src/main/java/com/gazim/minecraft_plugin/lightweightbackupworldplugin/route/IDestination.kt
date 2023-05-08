package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

interface IDestination<T : IDestination<T>> {
    val action: IAction
    val destinations: Set<T>
}