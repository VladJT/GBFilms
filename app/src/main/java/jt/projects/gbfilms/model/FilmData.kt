package jt.projects.gbfilms.model


sealed class FilmData {
    data class Success(val filmList: List<Film>) : FilmData()
    data class Error(val error: Throwable) : FilmData()
    data class Loading(val progress: Int?) : FilmData()
}