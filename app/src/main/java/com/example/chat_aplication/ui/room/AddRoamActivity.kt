package com.example.chat_aplication.ui.room

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chat_aplication.R
import com.example.chat_aplication.databinding.ActivityAddRoamBinding
import com.example.chat_aplication.ui.BaseActivity
import com.example.chat_aplication.ui.home.HomeActivity
import com.example.chat_aplication.ui.room.RoomCategories.Companion.getCategory

class AddRoamActivity : BaseActivity<ActivityAddRoamBinding, AddRomViewModel>(), AddRomNav {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeSpinner()
    }

    lateinit var adapter: CategoriesAdapter

    fun initializeSpinner() {
        adapter = CategoriesAdapter(getCategory())
        viewBinding.contentAddRoom.spinnerCategory.adapter = adapter
        viewBinding.contentAddRoom.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var selectedCategory = adapter.getItem(position) as RoomCategories
                    //move the selected category to VM
                    viewModel.selectedCategory = selectedCategory

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_roam
    }

    override fun getViewModelId(): AddRomViewModel {
        return ViewModelProvider(this).get(AddRomViewModel::class.java)
    }

    override fun backToHome() {
        finish()
    }

    override fun goToHome() {
        var intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}