package io.github.mihodihasan.proandroiddev

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MainViewModel : AndroidViewModel{
    constructor(application: Application) : super(application)
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

    fun loadRepositories() {
        isLoading.set(true)
        gitRepoRepository.getGitRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}