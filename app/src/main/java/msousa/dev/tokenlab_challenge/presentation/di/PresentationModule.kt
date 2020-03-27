package msousa.dev.tokenlab_challenge.presentation.di

import msousa.dev.tokenlab_challenge.presentation.ui.details.MovieDetailsViewModel
import msousa.dev.tokenlab_challenge.presentation.ui.list.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal object PresentationModule {
    val module = module {
        viewModel {
            MoviesViewModel(
                getListMoviesUseCase = get()
            )
        }

        viewModel {
            MovieDetailsViewModel(
                getMovieUseCase = get()
            )
        }
    }
}