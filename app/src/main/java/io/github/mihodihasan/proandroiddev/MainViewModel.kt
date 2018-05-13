package io.github.mihodihasan.proandroiddev

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MainViewModel : ViewModel(){
    var repoModel: RepoModel = RepoModel()
    val text = ObservableField<String>("Old Data")

    val isLoading = ObservableField<Boolean>(false)

    var repositories = ArrayList<Repository>()

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

    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories = data
            }
        })
    }
}