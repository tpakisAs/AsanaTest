package com.asanarebel.test

import java.io.File
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() : MainActivityContract.Presenter {


    private var view: MainActivityContract.View? = null
    private var folders: List<String> = emptyList()
    override fun fetchFolder() {
        view?.openFilePicker()
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
        fetchFolder()
    }

    override fun loadFolder(path: String) {
        folders = emptyList()
        File(path).walk().forEach { folders += it.name }
        view?.loadFolderItems(folders)
    }

}