package msousa.dev.tokenlab_challenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import msousa.dev.tokenlab_challenge.data.model.IMovieDetails

@Entity(tableName = "partialMovieData")
data class MovieDetailsEntity (
    @PrimaryKey(autoGenerate = false)
    override var id: Long,
    @ColumnInfo(name = "vote_average")
    override var voteAverage: Float,
    override var title: String,
    @ColumnInfo(name = "poster_url")
    override var posterUrl: String,
    @Ignore
    override val releaseDate: String
) : IMovieDetails {

    constructor() : this(1L, 1f, "", "", "")
}