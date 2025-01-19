package com.example.vuelos.gson

import com.google.gson.annotations.SerializedName

// Clase raíz que representa la respuesta completa
data class CiudadGson(
    @SerializedName("batchcomplete") val batchComplete: String,
    @SerializedName("query") val query: Query
)

// Clase que representa el objeto "query"
data class Query(
    @SerializedName("pages") val pages: Map<String, Page>
)

// Clase que representa cada página dentro de "pages"
data class Page(
    @SerializedName("pageid") val pageId: Int,
    @SerializedName("ns") val ns: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("pageimage") val pageImage: String
)

// Clase que representa el objeto "thumbnail"
data class Thumbnail(
    @SerializedName("source") val source: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)
