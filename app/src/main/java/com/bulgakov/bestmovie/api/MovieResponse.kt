package com.bulgakov.bestmovie.api

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("has_more") val has_more : Boolean,
    @SerializedName("num_results") val num_results : Int,
    @SerializedName("results") val results : List<Result>
)

data class Result (
    @SerializedName("display_title") val display_title : String?,
    @SerializedName("mpaa_rating") val mpaa_rating : String,
    @SerializedName("critics_pick") val critics_pick : Int,
    @SerializedName("byline") val byline : String,
    @SerializedName("headline") val headline : String,
    @SerializedName("summary_short") val summary_short : String?,
    @SerializedName("publication_date") val publication_date : String,
    @SerializedName("opening_date") val opening_date : String,
    @SerializedName("date_updated") val date_updated : String,
    @SerializedName("link") val link : Link,
    @SerializedName("multimedia") val multimedia : Multimedia?
)

data class Link (
    @SerializedName("type") val type : String,
    @SerializedName("url") val url : String,
    @SerializedName("suggested_link_text") val suggested_link_text : String
)

data class Multimedia (
    @SerializedName("type") val type : String,
    @SerializedName("src") val src : String?,
    @SerializedName("height") val height : Int,
    @SerializedName("width") val width : Int
)