package com.asanarebel.test

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.folder_list_item.view.*

class FolderListAdapter(
    val items: List<String>,
    val context: Context,
    val clickHandler: FilesListClickHandler
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.folder_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvFolderName?.text = items.get(position)
        holder.itemView.setOnClickListener { clickHandler.onClick(items.get(position)) }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvFolderName = view.folder_name
}

interface FilesListClickHandler {
    fun onClick(file: String)
}