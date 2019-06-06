package com.asanarebel.test

interface MainActivityContract {
    interface View {
        fun loadFolderItems(items: List<String>)
        fun openFilePicker()
    }

    interface Presenter {
        fun fetchFolder()
        fun attach(view: MainActivityContract.View)
        fun loadFolder(path: String)
    }
}