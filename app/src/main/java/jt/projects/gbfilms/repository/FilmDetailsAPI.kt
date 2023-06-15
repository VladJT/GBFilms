package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmDetailsAPI {

    @GET("{lang}/API/Title/{API_KEY}/{id}")
    fun getFilmDetails(
        @Path("lang") lang: String,
        @Path("API_KEY") apiKey: String,
        @Path("id") filmId: String
    ): Single<DetailsDTO>
}
