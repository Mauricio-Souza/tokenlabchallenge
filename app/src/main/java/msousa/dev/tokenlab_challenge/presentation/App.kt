package msousa.dev.tokenlab_challenge.presentation

import android.app.Application
import msousa.dev.tokenlab_challenge.data.data_source.local.DatabaseProvider
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository
import msousa.dev.tokenlab_challenge.domain.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.domain.GetMovieUseCase
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class App : Application() {

    val database = module {
        single { DatabaseProvider(get()) }
        single { get<DatabaseProvider>().fullMovieDataDao() }
        single { get<DatabaseProvider>().partialMovieDataDao() }
    }

    val viewModel = module {
        single { MoviesViewModel(get()) }
    }

    val usecase = module {
        single { GetMovieUseCase(get(), get()) }
        single { GetListMoviesUseCase(get(), get()) }
    }

    val repository = module {
        single { MoviesRepository(get(), get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this@App, listOf(viewModel, repository, usecase, database))
    }
}