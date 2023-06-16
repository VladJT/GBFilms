package jt.projects.gbfilms.interactors

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.repository.IFilmsRepo
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import jt.projects.gbfilms.repository.mappers.toFilmList


class FilmsInteractor(private val repo: IFilmsRepo) {

    fun getFilmsBySearchText(searchText: String): Single<List<Film>> =
        repo.getFilmsBySearchText(searchText)
            .map { filmDTO ->
                filmDTO.toFilmList()
            }
            .onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }

    fun getFilmDetailsByFilmId(filmId: String): Single<DetailsDTO> =
        repo.getFilmDetailsById(filmId)
            .onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }

    fun getTop250Films(): Single<List<Film>> =
        repo.getTop250Films()
            .map { topFilmsDTO ->
                topFilmsDTO.toFilmList()
            }
            .onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }

}