package com.rrat.lyricsapp.model.network

data class ArtistSong(
    val meta: Meta,
    val response: Response
)

data class Meta(
    val status: Int
)

data class Response(
    val next_page: Int,
    val songs: List<Song>
)

data class Song(
    val annotation_count: Int,
    val api_path: String,
    val artist_names: String,
    val featured_artists: List<FeaturedArtist>,
    val full_title: String,
    val header_image_thumbnail_url: String,
    val header_image_url: String,
    val id: Int,
    val lyrics_owner_id: Int,
    val lyrics_state: String,
    val path: String,
    val primary_artist: PrimaryArtist,
    val pyongs_count: Int,
    val relationships_index_url: String,
    val release_date_components: ReleaseDateComponents,
    val release_date_for_display: String,
    val song_art_image_thumbnail_url: String,
    val song_art_image_url: String,
    val stats: Stats,
    val title: String,
    val title_with_featured: String,
    val url: String
)

data class FeaturedArtist(
    val api_path: String,
    val header_image_url: String,
    val id: Int,
    val image_url: String,
    val iq: Int,
    val is_meme_verified: Boolean,
    val is_verified: Boolean,
    val name: String,
    val url: String
)

data class PrimaryArtist(
    val api_path: String,
    val header_image_url: String,
    val id: Int,
    val image_url: String,
    val iq: Int,
    val is_meme_verified: Boolean,
    val is_verified: Boolean,
    val name: String,
    val url: String
)

data class ReleaseDateComponents(
    val day: Int,
    val month: Int,
    val year: Int
)

data class Stats(
    val hot: Boolean,
    val pageviews: Int,
    val unreviewed_annotations: Int
)