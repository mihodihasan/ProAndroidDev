package io.github.mihodihasan.proandroiddev

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import io.github.mihodihasan.proandroiddev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding: ActivityMainBinding
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)
//    var mainViewModel = MainViewModel()

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
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter//RepositoryRecyclerViewAdapter(viewModel.repositories, this)

        viewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> { it?.let{ repositoryRecyclerViewAdapter.replaceData(it)} })
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
