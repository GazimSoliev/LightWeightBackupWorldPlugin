package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

interface IRouteDefiner {
    fun defineDestination(argRoute: IArgRoute, args: Collection<String>): IDestination<IArgDestination>
    fun defineAction(argRoute: IArgRoute, args: Collection<String>): IAction
    fun defineRoute(argRoute: IArgRoute, args: Collection<String>): List<String>
}