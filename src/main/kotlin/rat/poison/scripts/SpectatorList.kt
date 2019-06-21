//Credit to Mr. Noad for help

package rat.poison.scripts

import org.jire.arrowhead.Source
import rat.poison.App.haveTarget
import rat.poison.curSettings
import rat.poison.game.*
import rat.poison.game.CSGO.clientDLL
import rat.poison.game.CSGO.csgoEXE
import rat.poison.game.entity.*
import rat.poison.game.netvars.NetVarOffsets.m_hObserverTarget
import rat.poison.game.netvars.NetVarOffsets.m_iObserverMode
import rat.poison.game.offsets.ClientOffsets.dwIndex
import rat.poison.game.offsets.ClientOffsets.dwLocalPlayer
import rat.poison.game.offsets.EngineOffsets.dwClientState_State
import rat.poison.opened
import rat.poison.settings.MENUTOG
import rat.poison.strToBool
import rat.poison.ui.specListText
import rat.poison.utils.every
import rat.poison.utils.extensions.uint
import rat.poison.utils.notInGame

internal fun spectatorList() = every(1000) {
    if (!curSettings["SPECTATOR_LIST"]!!.strToBool() || notInGame || !curSettings["MENU"]!!.strToBool()) {
        return@every
    }

    var spectators = ""

    val playerSpecTarget: Int

    if (me.dead()) {
        playerSpecTarget = csgoEXE.readIndex(me + m_hObserverTarget)
    } else {
        playerSpecTarget = csgoEXE.readIndex(me + dwIndex)
    }

    forEntities body@ {
        val entity = it.entity

        if (it.type == EntityType.CCSPlayer) {
            if (entity.isSpectating() && !entity.hltv() && !entity.dormant()) {
                val entSpecTarget = csgoEXE.readIndex(entity + m_hObserverTarget)
                val entName = entity.name()

                if (entSpecTarget > -1 && entSpecTarget == playerSpecTarget) {
                    if (!spectators.contains(entName)) {
                        spectators += entName + "\n"
                    }
                }
            }
        }
        return@body false
    }

    if (opened && haveTarget) {
        specListText.setText(spectators)
    }
}

// Move to normal spot whenever
internal fun Player.observerMode(): Int = csgoEXE.int(this + m_iObserverMode)
internal fun Player.isSpectating(): Boolean = observerMode() > 0
fun Source.readIndex(address: Long) = int(address).toIndex()
fun Int.toIndex() = ((this and 0xFFF) - 1).run {
    if (this == 4094) -1 else this
}