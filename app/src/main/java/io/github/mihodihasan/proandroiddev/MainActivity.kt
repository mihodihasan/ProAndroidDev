package io.github.mihodihasan.proandroiddev

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import io.github.mihodihasan.proandroiddev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        binding.repositoryName.text = "Modern Android Medium Article"
//        binding.apply {
//            repositoryName.text = "Lushan"
//            repositoryOwner.text = "MH Lushan"
//            numberOfStarts.text = "1000 stars"
//
//        }
//        var repository = Repository("Medium Android Repository Article",
//                "Fleka", 1000, true)
//        binding.repository = repository
//        binding.executePendingBindings()
//        Handler().postDelayed({repository.repositoryName="New Name"},2000)
        binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.executePendingBindings()
    }
}
