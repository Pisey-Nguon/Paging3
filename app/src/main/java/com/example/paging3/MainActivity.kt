package com.example.paging3

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myAdapter = MyAdapter()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val lm = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvSample.layoutManager = lm
        binding.rvSample.adapter = myAdapter
        lifecycleScope.launch {
            viewModel.users.collectLatest {
                myAdapter.submitData(it)
            }
        }

        myAdapter.addLoadStateListener { loadState ->
            when (val state = loadState.source.refresh) {
                is LoadState.NotLoading -> {
                    /**
                     * Setting up the views as per your requirement
                     */
                    Log.d(TAG, "onCreate: NotLoading")
                }
                is LoadState.Loading -> {
                    /**
                     * Setting up the views as per your requirement
                     */
                    Log.d(TAG, "onCreate: Loading ")
                }
                is LoadState.Error -> {
                    /**
                     * Setting up the views as per your requirement
                     */
                    Log.d(TAG, "onCreate: Error")
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}