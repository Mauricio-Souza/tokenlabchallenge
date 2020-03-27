package msousa.dev.tokenlab_challenge.presentation

import android.app.Application
import msousa.dev.tokenlab_challenge.data.data_source.local.DatabaseProvider
import msousa.dev.tokenlab_challenge.domain.IMoviesRepository
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository
import msousa.dev.tokenlab_challenge.domain.usecases.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.domain.usecases.GetMovieUseCase
import msousa.dev.tokenlab_challenge.presentation.ui.details.MovieDetailsViewModel
import msousa.dev.tokenlab_challenge.presentation.ui.list.MoviesViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class App : Application() {

    val database = module {
        single { DatabaseProvider(get()) }
        single { get<DatabaseProvider>().movieEntityDao() }
        single { get<DatabaseProvider>().movieDetailsDao() }
    }

    val viewModel = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }
    }

    val usecase = module {
        single {
            GetMovieUseCase(
                get(),
                get()
            )
        }
        single {
            GetListMoviesUseCase(
                get(),
                get()
            )
        }
    }

    val repository = module {
        single<IMoviesRepository> { MoviesRepository(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this@App, listOf(viewModel, repository, usecase, database))
    }
}