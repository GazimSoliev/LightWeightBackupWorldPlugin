package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

class ArgRoute(
    override val action: IAction,
    vararg argDestinations: ArgDestination,
    override val destinations: Set<ArgDestination> = argDestinations.toSet()
) : IArgRoute

