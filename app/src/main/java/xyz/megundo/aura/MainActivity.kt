package xyz.megundo.aura

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.megundo.aura.data.RepositoryProvider
import xyz.megundo.aura.ui.AnimeAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)


        //load data from server and display on list
        val repository = RepositoryProvider.provideAnimeRepository()
        repository.getAnimeList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->

                    viewAdapter = AnimeAdapter(result)
                    recyclerView = findViewById<RecyclerView>(R.id.anime_list).apply {
                        setHasFixedSize(true)

                        layoutManager = viewManager


                        adapter = viewAdapter


                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

}

