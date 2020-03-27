package msousa.dev.tokenlab_challenge.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(entity: MovieEntity)

    @Query("select * from fullMovieData where id = :movieId")
    fun fetchMovieById(movieId: Long) : MovieEntity?
}