package com.example.nycschoolscores.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.databinding.SchoolRecyclerViewItemBinding

class SchoolAdapter : ListAdapter<School, SchoolAdapter.SchoolViewHolder>(SchoolsComparator()) {

    inner class SchoolViewHolder(val binding: SchoolRecyclerViewItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            SchoolRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = getItem(position)
        holder.binding.apply {
            schoolName.text = school.name
            neighborhood.text = school.neighborhood
        }

    }

    class SchoolsComparator : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem.id == newItem.id
    }
}