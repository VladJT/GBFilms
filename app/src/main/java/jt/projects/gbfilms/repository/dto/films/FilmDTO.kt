package jt.projects.gbfilms.repository.dto.films

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    val errorMessage: String,
    val expression: String,

    @SerializedName("results")
    val filmList: List<ResultDTO>,
    val searchType: String
)