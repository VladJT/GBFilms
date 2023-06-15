package jt.projects.gbfilms.repository.mappers

import jt.projects.gbfilms.model.Film
import jt.projects.gbfilms.repository.dto.FilmDTO

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