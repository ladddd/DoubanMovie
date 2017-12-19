package com.ladddd.douban

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.ladddd.douban.bean.MovieSubject
import com.ladddd.douban.presentImplement.MainPresenterImpl
import com.ladddd.douban.presenter.MainPresenter
import com.ladddd.douban.view.MainView
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), MainView{

    private lateinit var mainPresenter : MainPresenter
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = find(R.id.recyclerView) //anko
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainPresenter = MainPresenterImpl(this)
        mainPresenter.init()
    }

    override fun showTop250(subjects: List<MovieSubject>?) {
        Toast.makeText(this, subjects?.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showTop250Failed(message: String?) {
        Toast.makeText(this, message ?: return, Toast.LENGTH_LONG).show()
    }
}
