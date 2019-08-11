package com.asanarebel.test.reader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.asanarebel.test.Folder
import com.asanarebel.test.R
import kotlinx.android.synthetic.main.activity_reader.*

class ReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_reader)
        showFile(Folder.selectedFile ?: "")
        button_next.setOnClickListener {
            Folder.selectedFile?.apply {
                loadNext(Folder.listOfFiles.indexOfFirst { it.equals(this) })
            }
        }

        button_previous.setOnClickListener {
            Folder.selectedFile?.apply {
                loadPrevious(Folder.listOfFiles.indexOfFirst { it.equals(this) })
            }
        }
    }

    private fun loadNext(selectedIndex: Int) {

        if (Folder.listOfFiles.size > 0) {
            val filePath = when (selectedIndex) {
                in 0 until Folder.listOfFiles.lastIndex -> Folder.listOfFiles[selectedIndex.plus(1)]
                Folder.listOfFiles.lastIndex -> Folder.listOfFiles.first()
                else -> ""
            }
            if (filePath.isNotEmpty()) {
                updateSelection(filePath)
                showFile(filePath)
            }
        }
    }

    private fun loadPrevious(selectedIndex: Int) {
        if (Folder.listOfFiles.size > 0) {
            val filePath = when (selectedIndex) {
                in 1 until Folder.listOfFiles.size -> Folder.listOfFiles[selectedIndex.minus(1)]
                0 -> Folder.listOfFiles.last()
                else -> ""
            }
            if (filePath.isNotEmpty()) {
                updateSelection(filePath)
                showFile(filePath)
            }
        }

    }

    private fun updateSelection(filePath: String) {
        Folder.selectedFile = filePath
    }

    private fun showFile(filePath: String) {
        Folder.selectedFile = filePath
        reader_view.loadUrl("file://" + Folder.path + "/" + filePath)
    }


}