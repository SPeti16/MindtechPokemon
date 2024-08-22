package com.example.mindtechpokemon.network

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class PokemonData(val count:Int, val next:String?, val previous: String?, val results: List<PokemonResults>)

@Serializable
data class PokemonResults(val name:String, val url:String)



@Serializable
data class Pokemon (
    val abilities: List<Ability>,
    //val base_experience: Int?,
    //val cries: Cries,
    //val forms: List<Form>,
    //val game_indices: List<GameIndice>,
    val height: Int,
    //val held_items: List<@Contextual Any>,
    //val id: Int,
    //val is_default: Boolean,
    //val location_area_encounters: String,
    //val moves: List<Move>,
    val name: String,
    //val order: Int,
    //val past_abilities: List<@Contextual Any>,
    //val past_types: List<@Contextual Any>,
    //val species: Species,
    val sprites: Sprites,
    //val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    companion object {
        fun empty(): Pokemon {
            return Pokemon(
                abilities = emptyList(),
                //base_experience = 0,
                //cries = Cries("", ""),
                //forms = emptyList(),
                //game_indices = emptyList(),
                height = 0,
                //held_items = emptyList(),
                //id = 0,
                //is_default = false,
                //location_area_encounters = "",
                //moves = emptyList(),
                name = "",
                //order = 0,
                //past_abilities = emptyList(),
                //past_types = emptyList(),
                //species = Species("", ""),
                sprites = Sprites(),
                //stats = emptyList(),
                types = emptyList(),
                weight = 0
            )
        }
    }
}
@Serializable
data class Ability (
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)
@Serializable
data class AbilityX (
    val name: String,
    val url: String
)
@Serializable
data class Cries (
    val latest: String,
    val legacy: String
)
@Serializable
data class Form (
    val name: String,
    val url: String
)
@Serializable
data class GameIndice (
    val game_index: Int,
    val version: Version
)
@Serializable
data class Version (
    val name: String,
    val url: String
)
@Serializable
data class Move (
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
@Serializable
data class MoveX (
    val name: String,
    val url: String
)
@Serializable
data class VersionGroupDetail (
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)
@Serializable
data class MoveLearnMethod (
    val name: String,
    val url: String
)
@Serializable
data class VersionGroup (
    val name: String,
    val url: String
)
@Serializable
data class Species(
    val name: String,
    val url: String
)
@Serializable
data class Sprites(
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,
    val other: Other? = null,
    //val versions: @Contextual Any? = null,
)
@Serializable
data class Other(
    val dream_world: DreamWorld?,
    val home: Home?,
    val official_artwork: OfficialArtwork? = null
)
@Serializable
data class DreamWorld(
    val front_default: String?,
    val front_female: String?
)
@Serializable
data class Home(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)
@Serializable
data class OfficialArtwork(
    val front_default: String?
)
@Serializable
data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)
@Serializable
data class StatX(
    val name: String,
    val url: String
)
@Serializable
data class Type(
    val slot: Int,
    val type: TypeX
)
@Serializable
data class TypeX(
    val name: String,
    val url: String
)