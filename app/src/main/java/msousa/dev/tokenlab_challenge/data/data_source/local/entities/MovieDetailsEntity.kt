package msousa.dev.tokenlab_challenge.data.data_source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partialMovieData")
data class MovieDetailsEntity (
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Float,
    var title: String,
    @ColumnInfo(name = "poster_url")
    var posterUrl: String,
    val releaseDate: String
)