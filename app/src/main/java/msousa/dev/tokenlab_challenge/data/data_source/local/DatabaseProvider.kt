package msousa.dev.tokenlab_challenge.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.FullMovieDataDao
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.PartialMovieDataDao
import msousa.dev.tokenlab_challenge.data.entities.DataConverter
import msousa.dev.tokenlab_challenge.data.entities.FullMovieDataEntity
import msousa.dev.tokenlab_challenge.data.entities.PartialMovieDataEntity

const val DATABASE_NAME = "tokenlab_challenge_db"

@Database(entities = [FullMovieDataEntity::class, PartialMovieDataEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun fullMovieDataDao() : FullMovieDataDao
    abstract fun partialMovieDataDao() : PartialMovieDataDao

    companion object {
        @Volatile private var instance: DatabaseProvider? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) : DatabaseProvider =
                instance ?: synchronized(LOCK) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        private fun buildDatabase(context: Context) : DatabaseProvider {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseProvider::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}