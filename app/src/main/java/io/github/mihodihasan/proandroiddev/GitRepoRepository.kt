package io.github.mihodihasan.proandroiddev

import android.os.Handler
import android.content.Context
import io.reactivex.Observable

class GitRepoRepository(context: Context) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()
    val netManager = NetManager(context)

//    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
//        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
//    }

    fun getGitRepositories(): Observable<ArrayList<Repository>> {
//        var arrayList = ArrayList<Repository>()
//        arrayList.add(Repository("First", "Owner 1", 100 , false))
//        arrayList.add(Repository("Second", "Owner 2", 30 , true))
//        arrayList.add(Repository("Third", "Owner 3", 430 , false))
//
//        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) },2000)
        netManager.isConnectedToInternet?.let {
//            operator let in Kotlin checks nullability and returns you a value inside it
            if (it) {
                remoteDataSource.getRepositories(object : OnRepoRemoteReadyCallback {
                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object : OnRepoLocalReadyCallback {
                    override fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }
//        remoteDataSource.getRepositories(object: OnRepoRemoteReadyCallback{
//            override fun onRemoteDataReady(data: ArrayList<Repository>) {
//                localDataSource.saveRepositories(data)
//                onRepositoryReadyCallback.onDataReady(data)
//            }
//        })
    }
}

//interface OnDataReadyCallback {
//    fun onDataReady(data : String)
//}

//interface OnRepositoryReadyCallback {
//    fun onDataReady(data: ArrayList<Repository>)
//}