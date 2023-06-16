package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import jt.projects.gbfilms.repository.dto.films.FilmDTO
import jt.projects.gbfilms.repository.dto.topfilms.TopFilmsDTO

interface IFilmsRepo {
    fun getFilmsBySearchText(searchText: String): Single<FilmDTO>

    fun getFilmDetailsById(filmId: String): Single<DetailsDTO>

    fun getTop250Films(): Single<TopFilmsDTO>
}