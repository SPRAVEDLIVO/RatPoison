package rat.poison.utils

import it.unimi.dsi.fastutil.longs.Long2ObjectMap
import rat.poison.utils.common.Vector
import rat.poison.utils.common.readCached

inline fun <R> Long.readCached(cache: Long2ObjectMap<Vector>, crossinline read: Vector.(Long) -> R): Vector
		= readCached(cache, { Vector() }, read)