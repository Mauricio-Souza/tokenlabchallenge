package msousa.dev.tokenlab_challenge.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import msousa.dev.tokenlab_challenge.data.entities.FullMovieDataEntity

@Dao
interface FullMovieDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(full: FullMovieDataEntity)

    @Query("select * from fullMovieData where id = :movieId")
    suspend fun fetchMovieById(movieId: Long) : FullMovieDataEntity?
}