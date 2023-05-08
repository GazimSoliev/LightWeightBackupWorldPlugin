package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

class ArgDestination(
    override val arg: String,
    override val action: IAction,
    vararg destinations: ArgDestination,
) : IArgDestination {
    override val destinations = destinations.toSet()
}