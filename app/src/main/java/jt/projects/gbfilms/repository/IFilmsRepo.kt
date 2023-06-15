package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.FilmDTO
import retrofit2.http.Path

interface IFilmsRepo {
    fun getFilmsBySearchText(searchText: String): Single<FilmDTO>
}