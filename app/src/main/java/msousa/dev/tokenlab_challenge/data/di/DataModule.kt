package msousa.dev.tokenlab_challenge.data.di

import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository
import msousa.dev.tokenlab_challenge.domain.IMoviesRepository
import org.koin.dsl.module

internal object DataModule {
    val module = module {
        single<IMoviesRepository> {
            MoviesRepository(
                movieDao = get(),
                movieDetailsDao = get()
            )
        }
    }
}