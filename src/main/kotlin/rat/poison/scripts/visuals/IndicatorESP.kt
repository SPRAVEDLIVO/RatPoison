package rat.poison.scripts.visuals

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import rat.poison.curSettings
import rat.poison.game.*
import rat.poison.game.entity.*
import rat.poison.overlay.App
import rat.poison.overlay.App.shapeRenderer
import rat.poison.settings.DANGER_ZONE
import rat.poison.utils.common.Vector
import rat.poison.utils.common.inGame
import rat.poison.utils.common.normalize
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin
private const val id = "indicator"
private val forEnts = arrayOf(EntityType.CCSPlayer, EntityType.CPlantedC4, EntityType.CC4)
fun indicatorEsp() = App {
    if (!curSettings.bool["ENABLE_ESP"] || !curSettings.bool["INDICATOR_ESP"] || !inGame) return@App

    val bomb: Entity = entityByType(EntityType.CC4)?.entity ?: -1L
    val bEnt = bomb.carrier()

    forEntities(forEnts, iterateWeapons = true, iterateGrenades = true, identifier = id) {
        val entity = it.entity
        val onTeam = !DANGER_ZONE && meTeam == entity.team()

        var color = ""

        when (it.type) {
            EntityType.CCSPlayer -> {
                if (entity.dead() || entity == me || entity.dormant()) return@forEntities

                if (curSettings.bool["INDICATOR_SMOKE_CHECK"] && lineThroughSmoke(entity)) return@forEntities

                if (bEnt > 0 && bEnt == entity) { //This is the bomb carrier
                    if (curSettings.bool["INDICATOR_SHOW_ENEMIES"] && !onTeam) {
                        color = when (curSettings.bool["INDICATOR_SHOW_BOMB_CARRIER"]) {
                            true -> "INDICATOR_BOMB_CARRIER_COLOR"
                            false -> "INDICATOR_ENEMY_COLOR"
                        }
                    } else if (curSettings.bool["INDICATOR_SHOW_TEAM"] && onTeam) {
                        color = when (curSettings.bool["INDICATOR_SHOW_BOMB_CARRIER"]) {
                            true -> "INDICATOR_BOMB_CARRIER_COLOR"
                            false -> "INDICATOR_TEAM_COLOR"
                        }
                    }
                } else {
                    if (!curSettings.bool["INDICATOR_SHOW_TEAM"] && onTeam) {
                        return@forEntities
                    } else if (!curSettings.bool["INDICATOR_SHOW_ENEMIES"]) {
                        return@forEntities
                    } else {
                        color = when (!onTeam) {
                            true -> "INDICATOR_ENEMY_COLOR"
                            false -> "INDICATOR_TEAM_COLOR"
                        }
                    }
                }
            }

            EntityType.CPlantedC4, EntityType.CC4 -> {
                if (curSettings.bool["INDICATOR_SHOW_BOMB"]) {
                    color = "INDICATOR_BOMB_COLOR"
                }
            }

            EntityType.CEconEntity -> if (curSettings.bool["INDICATOR_SHOW_DEFUSERS"]) {
                color = "INDICATOR_DEFUSER_COLOR"
            }

            else -> {
                if (curSettings.bool["INDICATOR_SHOW_WEAPONS"] && it.type.weapon) {
                    color = "INDICATOR_WEAPON_COLOR"
                } else if (curSettings.bool["INDICATOR_SHOW_GRENADES"] && it.type.grenade) {
                    color = "INDICATOR_GRENADE_COLOR"
                }
            }
        }

        if (color != "") {
            drawIndicator(entity, color)
        }
    }
}

private val angsVec = Vector()
fun calcAngle(src: Vector, dest: Vector, vAng: Vector): Vector {
    val delta = src - dest
    val angs =  angsVec.set((Math.toDegrees(atan2(-delta.z, hypot(delta.x, delta.y)).toDouble()) - vAng.x).toFloat(), (Math.toDegrees(atan2(delta.y, delta.x).toDouble()) - vAng.y).toFloat(), 0F)
    angs.normalize()

    return angs
}

fun angVec(ang: Vector): Vector {
    val sy = sin(ang.y / 180.0 * Math.PI)
    val cy = cos(ang.y / 180.0 * Math.PI)
    val sp = sin(ang.x / 180.0 * Math.PI)
    val cp = cos(ang.x / 180.0 * Math.PI)

    return ang.set((cp * cy).toFloat(), (cp * sy).toFloat(), (-sp).toFloat())
}
private val meAbs = Vector()
private val meAngVec = Vector()
private val tAbs = Vector()
private val emptyVec = Vector()
private val triangPos = Vector()
private val meAng = Vector()
fun drawIndicator(enemyEnt: Long, col: String)
{
    val dist = curSettings.float["INDICATOR_DISTANCE"] * 10F
    val size = curSettings.float["INDICATOR_SIZE"]

    val meEyeAngle = me.eyeAngle(meAng)

    val tWidth = CSGO.gameWidth
    val tHeight = CSGO.gameHeight

    val meAbs = me.absPosition(meAbs)
    val entAbs = enemyEnt.absPosition(tAbs)

    val src = meAbs.apply { z = 0F }
    val dest = entAbs.apply { z = 0F }

    var tmpAng = calcAngle(src, dest, emptyVec)
    tmpAng = angVec(meAngVec.set(-tmpAng.x , 90F - tmpAng.y + meEyeAngle.y, -tmpAng.z))

    val triangPos = triangPos.set((tWidth / 2F) + (-tmpAng.x * dist), (tHeight / 2F) + (tmpAng.y * dist), 0F + (tmpAng.z * dist))

    if (!shapeRenderer.isDrawing) {
        shapeRenderer.begin()
    }

    shapeRenderer.set(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.color = curSettings.colorGDX[col]

    val rot = -atan2(triangPos.x - tWidth/2.0, triangPos.y - tHeight/2.0)

    //Middle of triangle
    val triangX = triangPos.x
    val triangY = triangPos.y

    val sin = size*sin(rot)
    val cos = size*cos(rot)

    //Rotate triangle
    val vert1x = (-sin + triangX).toFloat()
    val vert1y = (cos + triangY).toFloat()

    val vert2x = (-cos + sin + triangX).toFloat()
    val vert2y = (-sin - cos + triangY).toFloat()

    val vert3x = (cos + sin + triangX).toFloat()
    val vert3y = (sin - cos + triangY).toFloat()

    shapeRenderer.triangle(vert1x, vert1y, vert2x, vert2y, vert3x, vert3y)

    shapeRenderer.color = Color.BLACK
    shapeRenderer.set(ShapeRenderer.ShapeType.Line)
    shapeRenderer.end()
}