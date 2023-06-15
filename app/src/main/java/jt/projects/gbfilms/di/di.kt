package jt.projects.gbfilms.di


import jt.projects.gbfilms.App
import jt.projects.gbfilms.interactors.FilmsInteractor
import jt.projects.gbfilms.repository.FilmsRemoteDataSource
import jt.projects.gbfilms.repository.IFilmsRepo
import jt.projects.gbfilms.ui.details.DetailsViewModel
import jt.projects.gbfilms.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // контекст приложения
    single<App> { androidApplication().applicationContext as App }
}

val repoModule = module {
    // interactors
    single<FilmsInteractor> { FilmsInteractor(repo = get<IFilmsRepo>()) }

    // data sources
    single<IFilmsRepo> { FilmsRemoteDataSource() }
}


val vmModule = module {
    viewModel { HomeViewModel(interactor = get()) }
    viewModel { DetailsViewModel(interactor = get()) }
}
