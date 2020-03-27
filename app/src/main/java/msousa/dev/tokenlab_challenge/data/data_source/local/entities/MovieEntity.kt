package msousa.dev.tokenlab_challenge.data.data_source.local.entities

import androidx.room.*

@Entity(tableName = "fullMovieData")
data class MovieEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_url")
    val backdropUrl: String,
    @TypeConverters(DataConverter::class)
    val genres: List<String>,
    val title: String,
    val tagline: String,
    val overview: String,
    val popularity: Float,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    val runtime: Int,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val status: String
)