package rat.poison.game

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import rat.poison.game.entity.EntityType
import rat.poison.game.entity.Player
import rat.poison.game.hooks.constructingEntities
import rat.poison.settings.MAX_ENTITIES

@Volatile
var me: Player = 0
@Volatile
var clientState: ClientState = 0
@Volatile
var meTeam: Long = 0

typealias EntityList = Object2ObjectOpenHashMap<EntityType, MutableList<EntityContext>>

var entitiesValues = arrayOfNulls<MutableList<EntityContext>>(MAX_ENTITIES)
var entitiesValuesCounter = 0

val entities: EntityList = EntityList(EntityType.size).apply {
	for (type in EntityType.cachedValues) {
		val list = mutableListOf<EntityContext>()
		put(type, list)
		entitiesValues[entitiesValuesCounter++] = list
	}
}

fun entityByType(type: EntityType): EntityContext? = entities[type]?.firstOrNull()
data class EntityCache(var created: Long, var ents: ArrayList<EntityContext>, var iterating: Boolean = false)
val entityCache = Object2ObjectOpenHashMap<String, EntityCache>()

internal inline fun forEntities(types: Array<EntityType>, iterateWeapons: Boolean = false, iterateGrenades: Boolean = false, identifier: String, crossinline body: (EntityContext) -> Unit) {
	var get = entityCache[identifier]

	if (get == null) {
		val tmpClass = EntityCache(System.currentTimeMillis(), ArrayList())
		get = tmpClass
		entityCache[identifier] = tmpClass
	}

	if (!constructingEntities && System.currentTimeMillis() - get.created > 2000) {
		get.ents.clear()
		get.created = System.currentTimeMillis()

		for (i in 0 until types.size) {
			val element = types[i]
			val ents = entities[element] ?: continue
			for (i1 in 0 until ents.size) {
				val ent = ents[i1]
				get.ents.add(ent)
				ent.run(body)
			}
		}

		if (iterateWeapons) {
			for (i in 0 until EntityType.weaponsTypes.size) {
				val element = EntityType.weaponsTypes[i]
				val ents = entities[element] ?: continue
				for (i1 in 0 until ents.size) {
					val ent = ents[i1]
					get.ents.add(ent)
					ent.run(body)
				}
			}
		}

		if (iterateGrenades) {
			for (i in 0 until EntityType.grenadeTypes.size) {
				val element = EntityType.weaponsTypes[i]
				val ents = entities[element] ?: continue
				for (i1 in 0 until ents.size) {
					val ent = ents[i1]
					get.ents.add(ent)
					ent.run(body)
				}
			}
		}
	}
	else {
		if (!get.iterating) {
			get.iterating = true
			for (i in 0 until get.ents.size) {
				get.ents[i].run(body)
			}
			get.iterating = false
		}
	}
}