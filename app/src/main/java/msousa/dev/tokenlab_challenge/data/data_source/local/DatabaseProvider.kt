package msousa.dev.tokenlab_challenge.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.MovieDao
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.MovieDetailsDao
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.DataConverter
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.MovieEntity
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.MovieDetailsEntity

const val DATABASE_NAME = "tokenlab_challenge_db"

@Database(entities = [MovieEntity::class, MovieDetailsEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun movieEntityDao(): MovieDao
    abstract fun movieDetailsDao(): MovieDetailsDao

    companion object {
        @Volatile
        private var instance: DatabaseProvider? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): DatabaseProvider =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): DatabaseProvider {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseProvider::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}