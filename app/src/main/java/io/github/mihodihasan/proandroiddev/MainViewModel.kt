package io.github.mihodihasan.proandroiddev

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class MainViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var disposable: Disposable

    private val compositeDisposable = CompositeDisposable()

    var gitRepoRepository: GitRepoRepository = GitRepoRepository(getApplication())

    val text = ObservableField("old data")

    val isLoading = ObservableField(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()

//    val onDataReadyCallback = object : OnDataReadyCallback {
//        override fun onDataReady(data: String) {
//            isLoading=false
//            text=data
//        }
//    }
//
//    fun refresh(){
//        isLoading=true
//        repoModel.refreshData(onDataReadyCallback)
//    }

//    fun refresh() {
//        repoModel.refreshData( object : OnDataReadyCallback {
//            override fun onDataReady(data: String) {
//                isLoading.set(false)
//                text.set( data)
//            }})
//        }

//    fun loadRepositories() {
//        isLoading.set(true)
//        gitRepoRepository.getGitRepositories().subscribe(object: Observer<ArrayList<Repository>> {
//            override fun onSubscribe(d: Disposable) {
//                //todo
//                disposable=d
//            }
//
//            override fun onError(e: Throwable) {
//                //todo
//            }
//
//            override fun onNext(data: ArrayList<Repository>) {
//                repositories.value = data
//            }
//
//            override fun onComplete() {
//                isLoading.set(false)
//            }
//        })
//    }

//    fun loadRepositories() {
//        isLoading.set(true)
//        disposable = gitRepoRepository.getGitRepositories().subscribeWith(object: DisposableObserver<ArrayList<Repository>>() {
//
//            override fun onError(e: Throwable) {
//                // todo
//            }
//
//            override fun onNext(data: ArrayList<Repository>) {
//                repositories.value = data
//            }
//
//            override fun onComplete() {
//                isLoading.set(false)
//            }
//        })
//    }

    fun loadRepositories() {
        isLoading.set(true)
        compositeDisposable.add(gitRepoRepository.getGitRepositories().subscribeWith(object : DisposableObserver<ArrayList<Repository>>() {

            override fun onError(e: Throwable) {
                //if some error happens in our data layer our app will not crash, we will
                // get error here
            }

            override fun onNext(data: ArrayList<Repository>) {
                repositories.value = data
            }

            override fun onComplete() {
                isLoading.set(false)
            }
        }))
    }

    //    override fun onCleared() {
//        super.onCleared()
//        if(!disposable.isDisposed){
//            disposable.dispose()
//        }
//    }
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}