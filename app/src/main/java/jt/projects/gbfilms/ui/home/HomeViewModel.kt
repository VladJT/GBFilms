package jt.projects.gbfilms.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbfilms.model.FilmData


class HomeViewModel : ViewModel() {

    private val liveData: MutableLiveData<FilmData> = MutableLiveData()
    val liveDataToObserve: LiveData<FilmData>
        get() {
            return liveData
        }

    private val interactor = HomeInteractor()

    @SuppressLint("CheckResult")
    fun loadFilmsBySearchText(searchText: String) {
        liveData.value = FilmData.Loading(null)

        if (searchText.isBlank()) {
            liveData.value = FilmData.Error(RuntimeException("Введите текст для поиска"))
        } else {
            interactor.getFilms(searchText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    liveData.value = FilmData.Success(data)
                }, { e ->
                    liveData.value = FilmData.Error(e)
                })
        }
    }


}