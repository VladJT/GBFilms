package jt.projects.gbfilms.ui.home

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.BuildConfig
import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.repository.FilmsRemoteDataSource
import jt.projects.gbfilms.repository.mappers.toFilmList


class HomeInteractor(private val api: FilmsRemoteDataSource = FilmsRemoteDataSource()) {

    fun getFilms(searchText: String): Single<List<Film>> =
        api.getApi().getFilms(BuildConfig.API_KEY, searchText)
            .map { filmDTO ->
                filmDTO.toFilmList()
            }


}