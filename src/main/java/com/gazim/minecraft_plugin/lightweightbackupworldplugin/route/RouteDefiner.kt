package com.gazim.minecraft_plugin.lightweightbackupworldplugin.route

object RouteDefiner : IRouteDefiner {

    override fun defineDestination(argRoute: IArgRoute, args: Collection<String>): IDestination<IArgDestination> {
        var destinations: IDestination<IArgDestination> = argRoute
        for (arg in args) destinations.destinations
            .find { arg == it.arg }?.let { destinations = it } ?: break
        return destinations
    }

    override fun defineAction(argRoute: IArgRoute, args: Collection<String>): IAction =
        defineDestination(argRoute, args).action

    override fun defineRoute(argRoute: IArgRoute, args: Collection<String>): List<String> {
        val shortList = args.toMutableList()
        val last = shortList.removeLastOrNull() ?: return emptyList()
        val destination = defineDestination(argRoute, shortList)
        return destination.destinations
            .map(IArgDestination::arg).filter { it.contains(last) }
    }

}

fun IArgRoute.defineAction(args: Collection<String>) = RouteDefiner.defineAction(this, args)
fun IArgRoute.defineRoute(args: Collection<String>) = RouteDefiner.defineRoute(this, args)