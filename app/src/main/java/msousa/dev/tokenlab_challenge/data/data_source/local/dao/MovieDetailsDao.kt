package msousa.dev.tokenlab_challenge.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(vararg entity: MovieDetailsEntity)

    @Query("select * from partialMovieData")
    fun getAllMovies() : List<MovieDetailsEntity>?

}