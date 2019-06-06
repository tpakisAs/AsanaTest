package com.asanarebel.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.obsez.android.lib.filechooser.ChooserDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainActivityContract.View {


    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        DaggerAppComponent.create().inject(this)
        supportActionBar?.title = getString(R.string.title)
        presenter.attach(this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.barmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_folder -> {
            openFilePicker()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun loadFolderItems(items: List<String>) {
        folders_recycler_view.layoutManager = LinearLayoutManager(this)
        folders_recycler_view.adapter = FolderListAdapter(items, this)
    }

    override fun openFilePicker() {
        ChooserDialog(this@MainActivity)
            .withFilter(true, false)
            .withChosenListener(object : ChooserDialog.Result {
                override fun onChoosePath(path: String, pathFile: File) {
                    presenter.loadFolder(path)
                }
            })
            .build()
            .show()
    }


}
