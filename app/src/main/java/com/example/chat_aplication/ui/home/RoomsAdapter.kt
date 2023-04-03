package com.example.chat_aplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_aplication.R
import com.example.chat_aplication.dataBase.models.Room
import com.example.chat_aplication.databinding.ItemRoomBinding

class RoomsAdapter(var items: List<Room>? = null) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    class ViewHolder(var viewBinding: ItemRoomBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(room: Room) {
            viewBinding.item = room
            viewBinding.invalidateAll()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: ItemRoomBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_room, parent, false
            )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items!![position])

        onItemClickListener.let { clickListener ->
            holder.viewBinding.root.setOnClickListener {
                clickListener?.onItemClick(position, items!![position])
            }

        }

    }

    override fun getItemCount(): Int = items?.size ?: 0
    fun changeData(newLis: List<Room>?) {
        items = newLis
        notifyDataSetChanged()
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, items: Room)
    }
}