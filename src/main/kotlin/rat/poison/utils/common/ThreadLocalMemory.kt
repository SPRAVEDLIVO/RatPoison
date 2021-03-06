package rat.poison.utils.common

import com.sun.jna.Memory

fun threadLocalPointer(size: Int): ThreadLocal<Memory> = ThreadLocal.withInitial { Memory(size.toLong()) }