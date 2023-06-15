package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.FilmDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsAPI {
    @GET("API/Search/{API_KEY}/{searchText}")
    fun getFilms(
        @Path("API_KEY") apiKey: String,
        @Path("searchText") searchText: String
    ): Single<FilmDTO>
}
