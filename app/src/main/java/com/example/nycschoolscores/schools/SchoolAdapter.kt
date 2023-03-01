package com.example.nycschoolscores.schools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.databinding.SchoolRecyclerViewItemBinding

/**
 * Recyclerview Adapter for list of schools presented
 */
class SchoolAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<School, SchoolAdapter.SchoolViewHolder>(SchoolsComparator()) {

    class OnClickListener(val clickListener: (school: School) -> Unit) {
        fun onClick(school: School) = clickListener(school)
    }

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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(school)
        }

    }

    class SchoolsComparator : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem.id == newItem.id
    }
}