package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.films.FilmDTO

interface IFilmsRepo {
    fun getFilmsBySearchText(searchText: String): Single<FilmDTO>
}