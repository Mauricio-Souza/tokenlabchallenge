package msousa.dev.tokenlab_challenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import msousa.dev.tokenlab_challenge.data.model.PartialMovieDataProps

@Entity(tableName = "partialMovieData")
data class PartialMovieDataEntity (
    @PrimaryKey(autoGenerate = false)
    override val id: Long,
    @ColumnInfo(name = "vote_average")
    override val voteAverage: Float,
    override val title: String,
    @ColumnInfo(name = "poster_url")
    override val posterUrl: String,
    @Ignore
    override val releaseDate: String
) : PartialMovieDataProps