// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json    = Json(JsonConfiguration.Stable)
// val airport = json.parse(Airport.serializer(), jsonString)

package com.example.jsonbenchmark

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class AirportElement (
    val name: String? = null,
    val iata: String? = null,
    val icao: String? = null,
    val coordinates: List<Double>? = null,
    val runways: List<Runway>? = null
)

@Serializable
data class Runway (
    val direction: String? = null,
    val distance: Long? = null,
    val surface: Surface? = null
)

@Serializable
enum class Surface(val value: String) {
    Flexible("flexible"),
    Gravel("gravel"),
    Other("other"),
    Rigid("rigid"),
    Sealed("sealed"),
    Unpaved("unpaved");

    companion object : KSerializer<Surface> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("com.example.jsonbenchmark.Surface", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Surface = when (val value = decoder.decodeString()) {
            "flexible" -> Flexible
            "gravel"   -> Gravel
            "other"    -> Other
            "rigid"    -> Rigid
            "sealed"   -> Sealed
            "unpaved"  -> Unpaved
            else       -> throw IllegalArgumentException("Surface could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Surface) {
            return encoder.encodeString(value.value)
        }
    }
}
