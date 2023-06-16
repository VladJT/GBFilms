package jt.projects.gbfilms.repository


import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.BuildConfig
import jt.projects.gbfilms.repository.dto.details.DetailsDTO
import jt.projects.gbfilms.repository.dto.films.FilmDTO
import jt.projects.gbfilms.repository.dto.topfilms.TopFilmsDTO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FilmsRemoteDataSource : IFilmsRepo {

    private inline fun <reified T> getApi(): T =
        Retrofit.Builder().baseUrl(jt.projects.gbfilms.BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient(BaseInterceptor.interceptor))
            .build()
            .create(T::class.java)

    override fun getFilmsBySearchText(searchText: String): Single<FilmDTO> =
        getApi<FilmsAPI>().getFilms(apiKey = BuildConfig.API_KEY, searchText = searchText)

    override fun getFilmDetailsById(filmId: String): Single<DetailsDTO> =
        getApi<FilmDetailsAPI>().getFilmDetails(
            lang = "ru",
            apiKey = BuildConfig.API_KEY,
            filmId = filmId
        )

    override fun getTop250Films(): Single<TopFilmsDTO> =
        getApi<TopFilmsAPI>().getTop250Films(
            lang = "ru",
            apiKey = BuildConfig.API_KEY
        )


    /**
     *     В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    Retrofit.Builder через метод client().
     */
    private fun createOkHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

}
