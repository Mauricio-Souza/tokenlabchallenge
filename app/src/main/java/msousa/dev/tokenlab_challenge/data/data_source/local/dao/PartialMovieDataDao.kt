package msousa.dev.tokenlab_challenge.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import msousa.dev.tokenlab_challenge.data.entities.PartialMovieDataEntity

@Dao
interface PartialMovieDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(partial: PartialMovieDataEntity)

    @Query("select * from partialMovieData")
    fun getAllMovies() : List<PartialMovieDataEntity>?

}