package jt.projects.gbfilms.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbfilms.interactors.FilmsInteractor
import jt.projects.gbfilms.model.FilmData
import jt.projects.gbfilms.model.FilmDetailsData
import jt.projects.gbfilms.repository.FilmsRemoteDataSource
import jt.projects.gbfilms.utils.disposeBy


class DetailsViewModel(private val interactor: FilmsInteractor) : ViewModel() {

    private val liveData: MutableLiveData<FilmDetailsData> = MutableLiveData()
    val liveDataToObserve: LiveData<FilmDetailsData>
        get() {
            return liveData
        }
    private val compositeDisposable = CompositeDisposable()


    fun loadFilmDetailsById(filmId: String) {
        liveData.value = FilmDetailsData.Loading(null)

        if (filmId.isBlank()) {
            liveData.value = FilmDetailsData.Error(RuntimeException("Пустой id фильма"))
        } else {
            interactor.getFilmDetailsByFilmId(filmId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    liveData.value = FilmDetailsData.Success(data)
                }, { e ->
                    liveData.value = FilmDetailsData.Error(e)
                })
                .disposeBy(compositeDisposable)
        }
    }

    fun clear() {
        compositeDisposable.dispose()
    }
}