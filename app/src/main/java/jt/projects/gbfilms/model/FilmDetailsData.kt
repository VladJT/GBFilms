package jt.projects.gbfilms.model

import jt.projects.gbfilms.repository.dto.details.DetailsDTO


sealed class FilmDetailsData {
    data class Success(val filmData: DetailsDTO) : FilmDetailsData()
    data class Error(val error: Throwable) : FilmDetailsData()
    data class Loading(val progress: Int?) : FilmDetailsData()
}