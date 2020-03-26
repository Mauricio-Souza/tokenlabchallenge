package msousa.dev.tokenlab_challenge.data.entities

import androidx.room.*
import msousa.dev.tokenlab_challenge.data.model.IFullMovieData

@Entity(tableName = "fullMovieData")
data class FullMovieDataEntity (
    @PrimaryKey(autoGenerate = false)
    override val id: Long,
    override val adult: Boolean,
    @ColumnInfo(name = "backdrop_url")
    override val backdropUrl: String,
    @TypeConverters(DataConverter::class)
    override val genres: List<String>,
    override val title: String,
    override val tagline: String,
    override val overview: String,
    override val popularity: Float,
    @ColumnInfo(name = "poster_url")
    override val posterUrl: String,
    @ColumnInfo(name = "vote_average")
    override val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    override val voteCount: Int,
    override val runtime: Int,
    @ColumnInfo(name = "release_date")
    override val releaseDate: String,
    override val status: String
) : IFullMovieData {

    @Ignore
    constructor() : this(1L, false, "", listOf(), "", "", "", 1f, "", 1.0, 1, 1, "", "")
}