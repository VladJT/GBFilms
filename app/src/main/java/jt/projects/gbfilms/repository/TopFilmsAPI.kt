package jt.projects.gbfilms.repository

import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import jt.projects.gbfilms.repository.dto.topfilms.TopFilmsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface TopFilmsAPI {

    @GET("{lang}/API/Top250Movies/{API_KEY}")
    fun getTop250Films(
        @Path("lang") lang: String,
        @Path("API_KEY") apiKey: String
    ): Single<TopFilmsDTO>
}