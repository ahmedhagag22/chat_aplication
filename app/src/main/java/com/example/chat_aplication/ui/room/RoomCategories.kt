package com.example.chat_aplication.ui.room

import com.example.chat_aplication.R

data class RoomCategories(
    val id: String,
    val name: String,
    val ImageId: Int,
) {

    companion object {

        fun getCategoryById(id: String?): RoomCategories {
            return when (id) {
                "sports" -> {
                    RoomCategories("sports", "Sports", R.drawable.sports)
                }

                "music" -> {
                    RoomCategories("music", "Music", R.drawable.music)
                }
                "movies" -> {
                    RoomCategories("movies", ",Movies", R.drawable.movies)
                }
                else->
                {
                    RoomCategories("sports", "Sports", R.drawable.sports)
                }


            }


        }

        fun getCategory() = listOf(

            RoomCategories("sports", "Sports", R.drawable.sports),
            RoomCategories("music", "Music", R.drawable.music),
            RoomCategories("movies", ",Movies", R.drawable.movies)
        )

    }
}


