package jt.projects.gbfilms.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbfilms.model.FilmData
import jt.projects.gbfilms.repository.FilmsRemoteDataSource
import jt.projects.gbfilms.utils.disposeBy


class HomeViewModel(private val interactor: HomeInteractor) : ViewModel() {

    private val liveData: MutableLiveData<FilmData> = MutableLiveData()
    val liveDataToObserve: LiveData<FilmData>
        get() {
            return liveData
        }
    private val compositeDisposable = CompositeDisposable()

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
                .disposeBy(compositeDisposable)
        }
    }

    fun clear() {
        compositeDisposable.dispose()
    }
}