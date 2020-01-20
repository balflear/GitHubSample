package com.github.example.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.example.R
import com.github.example.data.pojo.RepositoryResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_list_item.view.*

/**
 * Created by kostadin.georgiev on 1/7/2020.
 * Adapter responsible for populating github repositories on the screen
 */
class RepositoryAdapter(private val context: Context) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var repositoryList = ArrayList<RepositoryResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositoryList[position]

        holder.tvRepositoryName.text = repository.name
        holder.tvRepositoryDesc.text = repository.description
        holder.tvOwnerType.text = repository.owner.login + "(" + repository.owner.type + ")"

        repository.owner.avatar_url?.let {
            Picasso.get()
                .load(repository.owner.avatar_url)
                .fit()
                .centerCrop()
                .into(holder.ivRepositoryAvatar)
        }
    }

    fun updateValues(repositoryList: ArrayList<RepositoryResponse>) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepositoryName: TextView = itemView.tvRepositoryName
        val tvRepositoryDesc: TextView = itemView.tvRepositoryDesc
        val tvOwnerType: TextView = itemView.tvOwnerType
        val ivRepositoryAvatar: AppCompatImageView? = itemView.ivRepositoryAvatar
    }
}