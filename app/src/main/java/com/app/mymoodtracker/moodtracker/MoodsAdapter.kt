package com.app.mymoodtracker.moodtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.mymoodtracker.database.Moods
import com.app.mymoodtracker.databinding.MoodItemListBinding

class MoodsAdapter(private val clickListener: MoodListener) : ListAdapter<Moods, MoodsAdapter.MyViewHolder>(MoodsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class MyViewHolder private constructor(private val binding: MoodItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Moods, clickListener: MoodListener) {
            binding.mood = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MoodItemListBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }
}

class MoodsDiffCallback : DiffUtil.ItemCallback<Moods>() {
    override fun areItemsTheSame(oldItem: Moods, newItem: Moods): Boolean {
        return oldItem.moodId == newItem.moodId
    }

    override fun areContentsTheSame(oldItem: Moods, newItem: Moods): Boolean {
        return oldItem == newItem
    }
}

class MoodListener(val clickListener: (moodId: Long) -> Unit) {
    fun onClick(mood: Moods) = clickListener(mood.moodId)
}