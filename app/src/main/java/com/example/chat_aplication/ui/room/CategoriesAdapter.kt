package com.example.chat_aplication.ui.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.chat_aplication.R
import com.example.chat_aplication.databinding.ItemCategorySpinerBinding

class CategoriesAdapter(var items: List<RoomCategories>) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var currentView = convertView
        var viewHolder: ViewHolder
        if (currentView == null) {
            // create VH
            var viewBinding: ItemCategorySpinerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context), R.layout.item_category_spiner, parent, false
            )
            viewHolder = ViewHolder(viewBinding)
            currentView = viewHolder.viewBinding.root
            currentView.tag = viewHolder

        } else {
            viewHolder = currentView.tag as ViewHolder
        }
        //data Binding
        val item = items[position]
        viewHolder.viewBinding.item = item
        viewHolder.viewBinding.invalidateAll()

        //return view elly hyt3rd
        return currentView
    }

    class ViewHolder(var viewBinding: ItemCategorySpinerBinding)

}