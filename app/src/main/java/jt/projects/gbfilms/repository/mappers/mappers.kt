package jt.projects.gbfilms.repository.mappers

import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.repository.dto.films.FilmDTO
import jt.projects.gbfilms.repository.dto.topfilms.TopFilmsDTO

fun FilmDTO.toFilmList(): List<Film> {
    val result = mutableListOf<Film>()
    this.filmList.forEach { resultDTO ->
        result.add(
            Film(
                id = resultDTO.id ?: "",
                imageUrl = resultDTO?.image ?: "",
                title = resultDTO.title ?: "",
                description = resultDTO.description ?: "",
            )
        )
    }
    return result
}


fun TopFilmsDTO.toFilmList(): List<Film> {
    val result = mutableListOf<Film>()
    this.items.forEach { resultDTO ->
        result.add(
            Film(
                id = resultDTO.id,
                imageUrl = resultDTO?.image ?: "",
                title = resultDTO.fullTitle,
                description = resultDTO.crew,
                imDbRating = resultDTO.imDbRating,
                rank = resultDTO.rank
            )
        )
    }
    return result
}