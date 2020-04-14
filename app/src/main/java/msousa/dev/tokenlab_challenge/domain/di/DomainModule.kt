package msousa.dev.tokenlab_challenge.domain.di

import msousa.dev.tokenlab_challenge.domain.usecases.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.domain.usecases.GetMovieUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal object DomainModule {
    val module = module {
        single {
            GetListMoviesUseCase(
                context = androidApplication(),
                repository = get()
            )
        }

        single {
            GetMovieUseCase(
                context = androidApplication(),
                repository = get()
            )
        }
    }
}