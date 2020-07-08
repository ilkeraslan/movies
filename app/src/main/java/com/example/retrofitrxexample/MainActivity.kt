package com.example.retrofitrxexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var movies = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = host.navController

        navView.setupWithNavController(navController)
        navView.setOnNavigationItemReselectedListener { /* do nothing */ }

/*        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MoviesAdapter(movies)
        }*/

/*        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getMovies(resources.getString(R.string.api_key))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {response -> onResponse(response)},
                    {t -> onFailure(t) }
                )
        )

        val foo = CompositeDisposable()
        foo.add(
            ServiceBuilder.buildService().getUpcomingMovies(resources.getString(R.string.api_key))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {response -> onResponse(response)},
                    {t -> onFailure(t) }
                )
        )*/

/*        val bar: Observable<PopularMovies> =
            Observable.merge(
                ServiceBuilder.buildService().getMovies(resources.getString(R.string.api_key)),
                ServiceBuilder.buildService()
                    .getUpcomingMovies(resources.getString(R.string.api_key))
            )

        val disp = bar
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> onResponse(response) },
                { error -> onFailure(error) }
            )

        val compositeDisp = CompositeDisposable().add(disp)*/

        val foo = CompositeDisposable()
        foo.add(
            KtorServiceBuilder.buildService().getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> onResponse(response) },
                    { t -> onFailure(t) }
                ))

        val usersCompositeDisposable = CompositeDisposable().add(foo)
    }


    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(response: PopularMovies) {
        response.results.forEach {
            movies.add(it)
            println("MOVIE: ".plus(it.title))
        }
    }

    private fun onResponse(response: Users) {
        println(response)
    }
}