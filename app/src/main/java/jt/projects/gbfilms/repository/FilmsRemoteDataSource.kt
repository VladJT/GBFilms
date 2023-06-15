package jt.projects.gbfilms.repository


import io.reactivex.rxjava3.core.Single
import jt.projects.gbfilms.BuildConfig
import jt.projects.gbfilms.repository.dto.films.FilmDTO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class FilmsRemoteDataSource : IFilmsRepo {

    private fun getApi(): FilmsAPI = Retrofit.Builder().baseUrl(jt.projects.gbfilms.BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
          .client(createOkHttpClient(MyInterceptor()))
        .build()
        .create(FilmsAPI::class.java)

    override fun getFilmsBySearchText(searchText: String): Single<FilmDTO> =
        getApi().getFilms(BuildConfig.API_KEY, searchText)


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

    inner class MyInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

}
